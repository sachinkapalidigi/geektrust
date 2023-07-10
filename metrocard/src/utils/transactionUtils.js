const {
  STATIONS,
  PRICING,
  RETURN_DISCOUNT_PERCENTAGE,
  DEFAULT_DISCOUNT_AMOUNT,
  DEFAULT_SERVICE_CHARGE,
  SERVICE_CHARGE_PERCENTAGE,
} = require("../constants");

const getTicketPricing = (source, destination, userType) => {
  if (
    PRICING[source] &&
    PRICING[source][destination] &&
    PRICING[source][destination][userType]
  ) {
    return PRICING[source][destination][userType];
  }
  throw new Error("Invalid request for ticket");
};

const transformSourceDestination = (source, destination) => {
  if (!destination) {
    return {
      source,
      destination:
        source === STATIONS.AIRPORT ? STATIONS.CENTRAL : STATIONS.AIRPORT,
    };
  }
  return {
    source,
    destination,
  };
};

const getTicketDiscount = (
  stationTransaction,
  cardId,
  originalCost,
  source,
  destination
) => {
  const countDestination = stationTransaction[destination].filter(
    (tx) => tx.cardId === cardId && tx.source === destination
  ).length;
  const countSource = stationTransaction[source].filter(
    (tx) => tx.cardId === cardId && tx.source === source
  ).length;
  if (countDestination > countSource)
    return (originalCost * RETURN_DISCOUNT_PERCENTAGE) / 100;
  return DEFAULT_DISCOUNT_AMOUNT;
};

const getServiceCharge = (amountToPay, balance) => {
  if (balance > amountToPay) return DEFAULT_SERVICE_CHARGE;
  const rechargeAmount = amountToPay - balance;
  return (rechargeAmount * SERVICE_CHARGE_PERCENTAGE) / 100;
};

module.exports = {
  getTicketPricing,
  transformSourceDestination,
  getTicketDiscount,
  getServiceCharge,
};
