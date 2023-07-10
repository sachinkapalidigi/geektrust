const { STATIONS } = require("../constants");
const {
  getTicketPricing,
  transformSourceDestination,
  getTicketDiscount,
  getServiceCharge,
} = require("../utils/transactionUtils");
const {
  getSummary,
  printStationSummary,
} = require("../utils/transactionSummaryUtils");
const {
  getCardBalance,
  addToBalance,
  deductFromBalance,
} = require("./metroCardBalance");

const stationTransactions = {
  [STATIONS.AIRPORT]: [],
  [STATIONS.CENTRAL]: [],
};

function Transaction(
  source,
  destination,
  serviceCharge,
  discount,
  amountPaid,
  userType,
  cardId
) {
  this.source = source;
  this.destination = destination;
  this.serviceCharge = serviceCharge;
  this.discount = discount;
  this.amountPaid = amountPaid;
  this.userType = userType;
  this.cardId = cardId;
}

const makeCardPayment = (cardId, amountToPay, balance, serviceCharge) => {
  if (serviceCharge !== 0) {
    const rechargeAmount = amountToPay - balance;
    balance = addToBalance(cardId, rechargeAmount);
  }
  balance = deductFromBalance(cardId, amountToPay);
};

const calculateTransactionCharges = (
  cardId,
  userType,
  source,
  destination,
  stationTransactions
) => {
  const originalCost = getTicketPricing(source, destination, userType);

  if (!stationTransactions[destination] || !stationTransactions[source])
    throw new Error("Invalid route");

  const discount = getTicketDiscount(
    stationTransactions,
    cardId,
    originalCost,
    source,
    destination
  );

  const amountToPay = originalCost - discount;

  let balance = getCardBalance(cardId);
  const serviceCharge = getServiceCharge(amountToPay, balance);
  makeCardPayment(cardId, amountToPay, balance, serviceCharge);

  return {
    serviceCharge,
    discount,
    amountToPay,
  };
};

const makeTransaction = (
  cardId,
  userType,
  sourceStation,
  destinationStation
) => {
  const { source, destination } = transformSourceDestination(
    sourceStation,
    destinationStation
  );
  const { serviceCharge, discount, amountToPay } = calculateTransactionCharges(
    cardId,
    userType,
    source,
    destination,
    stationTransactions
  );
  const transaction = new Transaction(
    source,
    destination,
    serviceCharge,
    discount,
    amountToPay,
    userType,
    cardId
  );
  stationTransactions[source].push(transaction);
};

const printSummary = () => {
  // Central
  const centralSummary = getSummary(STATIONS.CENTRAL, stationTransactions);
  printStationSummary(STATIONS.CENTRAL, centralSummary);
  // Airports
  const airportSummary = getSummary(STATIONS.AIRPORT, stationTransactions);
  printStationSummary(STATIONS.AIRPORT, airportSummary);
};

module.exports = {
  makeTransaction,
  printSummary,
};
