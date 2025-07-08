import pandas as pd
import numpy as np
import matplotlib.pyplot as plt

# -------- Upravená funkcia pre nové Binance dáta --------
def load_and_prepare(filepath):
    # Načítanie CSV s hlavičkou
    df = pd.read_csv(filepath)
    # Normalize column names: trim a lowercase
    df.columns = df.columns.str.strip().str.lower()

    # Overenie, že stĺpce existujú
    expected_cols = {'open_time', 'open', 'high', 'low', 'close', 'volume'}
    missing = expected_cols - set(df.columns)
    if missing:
        raise ValueError(f"Chýbajúce stĺpce: {missing}")

    # Vytvorenie timestamp z open_time (milisekundy od epochy)
    df['timestamp'] = pd.to_datetime(df['open_time'], unit='ms', utc=True)
    df.set_index('timestamp', inplace=True)

    # Vyber len stĺpce, ktoré používaš
    df = df[['open', 'high', 'low', 'close', 'volume']].astype(float)

    # Vypočítanie cieľovej hodnoty o 3 intervaly dopredu
    future_price = df['close'].shift(-5)
    change = (future_price - df['close']) / df['close']

    # Definícia kategórií: pokles, neutrál, rast
    conditions = [
        change < -0.005,
        (change >= -0.005) & (change <= 0.005),
        change > 0.005
    ]
    choices = [0, 1, 2]
    df['target'] = np.select(conditions, choices)
    df.dropna(inplace=True)

    # Príprava vstupu X a výstupu y
    X = df[['open', 'high', 'low', 'close', 'volume']].values
    y_raw = df['target'].values.astype(int)

    # One-hot encoding cieľa
    y = np.zeros((len(y_raw), 3))
    y[np.arange(len(y_raw)), y_raw] = 1

    # Normalizácia X do [0,1]
    X = (X - X.min(axis=0)) / (X.max(axis=0) - X.min(axis=0) + 1e-8)

    print("Target distribúcia:", np.bincount(y_raw))
    return X, y, df

# -------- Aktivácie a utility --------
def relu(x): return np.maximum(0, x)
def relu_derivative(x): return (x > 0).astype(float)

def softmax(x):
    e_x = np.exp(x - np.max(x, axis=1, keepdims=True))
    return e_x / e_x.sum(axis=1, keepdims=True)

def cross_entropy(pred, true):
    return -np.mean(np.sum(true * np.log(pred + 1e-8), axis=1))

# -------- Mini-batch generator --------
def create_mini_batches(X, y, batch_size=128):
    m = X.shape[0]
    indices = np.random.permutation(m)
    X_shuffled = X[indices]
    y_shuffled = y[indices]

    mini_batches = []
    for i in range(0, m, batch_size):
        X_batch = X_shuffled[i:i+batch_size]
        y_batch = y_shuffled[i:i+batch_size]
        mini_batches.append((X_batch, y_batch))
    return mini_batches

# -------- Inicializácia s He metódou --------
def he_initialization(shape_in, shape_out):
    return np.random.randn(shape_in, shape_out) * np.sqrt(2. / shape_in)

# -------- Hlavný tréning --------
def train_model(filepaths, input_size=5, hidden1=64, hidden2=32, output_size=3, lr=0.01, epochs=700, batch_size=128):
    # podpora jedného súboru aj zoznamu súborov
    if isinstance(filepaths, str):
        filepaths = [filepaths]

    np.random.seed(42)

    W1 = he_initialization(input_size, hidden1)
    b1 = np.zeros((1, hidden1))
    W2 = he_initialization(hidden1, hidden2)
    b2 = np.zeros((1, hidden2))
    W3 = he_initialization(hidden2, output_size)
    b3 = np.zeros((1, output_size))

    for file in filepaths:
        try:
            print(f"\nTraining on {file}")
            X_train, y_train, _ = load_and_prepare(file)

            for epoch in range(epochs):
                mini_batches = create_mini_batches(X_train, y_train, batch_size)
                epoch_loss = 0
                correct = 0
                total = 0

                for X_batch, y_batch in mini_batches:
                    # forward pass
                    z1 = np.dot(X_batch, W1) + b1
                    a1 = relu(z1)

                    z2 = np.dot(a1, W2) + b2
                    a2 = relu(z2)

                    z3 = np.dot(a2, W3) + b3
                    a3 = softmax(z3)

                    # loss
                    loss = cross_entropy(a3, y_batch)
                    epoch_loss += loss

                    # accuracy
                    preds = np.argmax(a3, axis=1)
                    labels = np.argmax(y_batch, axis=1)
                    correct += np.sum(preds == labels)
                    total += len(labels)

                    # backpropagation
                    dz3 = (a3 - y_batch) / len(X_batch)
                    dW3 = np.dot(a2.T, dz3)
                    db3 = np.sum(dz3, axis=0, keepdims=True)

                    da2 = np.dot(dz3, W3.T)
                    dz2 = da2 * relu_derivative(a2)
                    dW2 = np.dot(a1.T, dz2)
                    db2 = np.sum(dz2, axis=0, keepdims=True)

                    da1 = np.dot(dz2, W2.T)
                    dz1 = da1 * relu_derivative(a1)
                    dW1 = np.dot(X_batch.T, dz1)
                    db1 = np.sum(dz1, axis=0, keepdims=True)

                    # update
                    W3 -= lr * dW3
                    b3 -= lr * db3
                    W2 -= lr * dW2
                    b2 -= lr * db2
                    W1 -= lr * dW1
                    b1 -= lr * db1

                if (epoch + 1) % 5 == 0:
                    accuracy = correct / total
                    avg_loss = epoch_loss / len(mini_batches)
                    print(f"Epoch {epoch+1}/{epochs} | Loss: {avg_loss:.4f} | Accuracy: {accuracy:.4f}")

        except Exception as e:
            print(f"Error in {file}: {e}")

    return W1, b1, W2, b2, W3, b3

# -------- Simulácia tradingu --------
def simulate_trading(filepaths, W1, b1, W2, b2, W3, b3):
    # podpora jedného súboru aj zoznamu
    if isinstance(filepaths, str):
        filepaths = [filepaths]

    for filepath in filepaths:
        print(f"\n--- Real-time simulation on {filepath} ---")
        X_test, y_test, df_test = load_and_prepare(filepath)

        # forward pass
        a3 = softmax(np.dot(relu(np.dot(relu(np.dot(X_test, W1)+b1), W2)+b2), W3)+b3)
        actions = np.argmax(a3, axis=1)

        balance = 20
        asset = 0.0
        max_trade_amount = 20
        buy_count = sell_count = hold_count = 0
        all_actions = []

        for i, action in enumerate(actions):
            price = df_test['close'].iloc[i]
            timestamp = df_test.index[i]
            prob = a3[i, action]
            trade_amount = max_trade_amount * prob

            if action == 2 and balance >= trade_amount:
                asset += trade_amount / price
                balance -= trade_amount
                buy_count += 1
                all_actions.append((timestamp, price, 'BUY'))
            elif action == 0 and asset > 0:
                sell_amt = min(asset, trade_amount/price)
                balance += sell_amt * price
                asset -= sell_amt
                sell_count += 1
                all_actions.append((timestamp, price, 'SELL'))
            else:
                hold_count += 1
                all_actions.append((timestamp, price, 'HOLD'))

        if asset > 0:
            final_price = df_test['close'].iloc[-1]
            balance += asset * final_price
            print(f"End of data: SELL remaining asset at {final_price:.4f}, final balance = {balance:.2f}")
        else:
            print(f"End of data: No assets left to sell. Final balance = {balance:.2f}")

        print(f"Total BUYs: {buy_count}, SELLs: {sell_count}, HOLDs: {hold_count}")
        print(f"Final balance: {balance:.2f}")

        # vykreslenie
        plt.figure(figsize=(12,6))
        plt.plot(df_test.index, df_test['close'], label='Price')
        buys = [(t,p) for t,p,a in all_actions if a=='BUY']
        sells = [(t,p) for t,p,a in all_actions if a=='SELL']
        if buys:
            bt, bp = zip(*buys)
            plt.scatter(bt, bp, marker='^', label='BUY')
        if sells:
            st, sp = zip(*sells)
            plt.scatter(st, sp, marker='v', label='SELL')
        plt.title('Trading Simulation')
        plt.xlabel('Time')
        plt.ylabel('Price')
        plt.legend()
        plt.show()

"""# -------- Spustenie -------- 5 minutove spustenie
filepaths = "uplne_nova_s_viac_vstupov/5_rokov_dat.csv"
W1, b1, W2, b2, W3, b3 = train_model(filepaths)
simulate_trading("uplne_nova_s_viac_vstupov/BTCUSDT_5m_full.csv", W1, b1, W2, b2, W3, b3)"""

# -------- Spustenie -------- hodinove spustenie
filepaths = "5_rokov_minus_6_mes_data.csv"
W1, b1, W2, b2, W3, b3 = train_model(filepaths)
simulate_trading("last_6_months_1h_data.csv", W1, b1, W2, b2, W3, b3)
