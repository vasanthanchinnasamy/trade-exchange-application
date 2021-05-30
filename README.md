### Trade Exchange Application

###Key Decisions

- I have chosen **in memory processing** for acheiving high throughput.
- I have seperated the order matching algorithm and trade insertion by using **Rabbit MQ** for keeping in memory processing  and Trade insertion as seperate services so that the in memory processing can be done fast and it can be scaled seperately.

###Task 1 - Order Matching
- Order matching is implemented in [ExchangeConsumer.java](https://github.com/vasanthanchinnasamy/trade-exchange-application/blob/master/src/main/java/com/innovate/consumer/ExchangeConsumer.java)
- I have assumed that it will receive orders as messages from Rabbit MQ;
- I have used in memory processing for matching orders;
- When orders are matched, a Rabbit MQ message was produced with trade details;
- These messages are consumed in [TradeConsumer.java](https://github.com/vasanthanchinnasamy/trade-exchange-application/blob/master/src/main/java/com/innovate/consumer/TradeConsumer.java) 
- The trade data are consumed and inserted into postgres database.

###Task 2 - Query list of trades with the ability to filter based on Parties, SYMBOL or Date

- Created a rest endpoint for this task in [ExchangeController.java](https://github.com/vasanthanchinnasamy/trade-exchange-application/blob/master/src/main/java/com/innovate/controller/ExchangeController.java#L39)

###Task 3 - Query list of orders that are not yet matched, with the ability to filter based on SYMBOL, Price

- Created a rest endpoint for this task in [ExchangeController.java](https://github.com/vasanthanchinnasamy/trade-exchange-application/blob/master/src/main/java/com/innovate/controller/ExchangeController.java#L51)

###Others

- Tests are created for all the solutions of the above tasks in [TradeExchangeApplicationTests.java](https://github.com/vasanthanchinnasamy/trade-exchange-application/blob/master/src/test/java/com/innovate/TradeExchangeApplicationTests.java)
