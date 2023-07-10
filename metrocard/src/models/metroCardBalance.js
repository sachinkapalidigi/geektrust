const metroCards = {};

const setCardBalance = (cardId, balance) => {
  if (!cardId) throw new Error(`Invalid User ID`);
  metroCards[cardId] = parseInt(balance);
  return metroCards[cardId];
};

const getCardBalance = (cardId) => {
  if (!metroCards[cardId]) return 0;
  return metroCards[cardId];
};

const addToBalance = (cardId, amount) => {
  if (!metroCards[cardId]) setCardBalance(cardId, 0);

  metroCards[cardId] += parseInt(amount);
  return metroCards[cardId];
};

const deductFromBalance = (cardId, amount) => {
  if (!metroCards[cardId] || metroCards[cardId] < amount)
    throw new Error(`Low balance found for card ${cardId}`);
  metroCards[cardId] -= parseInt(amount);
  return metroCards[cardId];
};

module.exports = {
  setCardBalance,
  getCardBalance,
  addToBalance,
  deductFromBalance,
};
