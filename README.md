# CodeAlpha_StockTradingPlatform
A console-based Java application that simulates a basic stock trading environment. The system is built using object-oriented principles, with four core classes:
Stock: epresents a tradable stock with a symbol, name, and price that changes randomly to simulate market movement.
Transaction: records a buy or sell trade that includes the stock, quantity, and the price.
Users: represents a trader with a cash balance, a portfolio and their own transaction history. .
TradingPlatform: the central manager class, holding all stocks and users. Handles adding stocks/users, displaying market data, executing trades on a user's behalf, simulating daily price changes, and persisting all user data (cash, portfolio, transaction history) to a file so progress survives between sessions.