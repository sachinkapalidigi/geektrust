const { USER_TYPES } = require("../constants");

const orderUserTypeCount = (adult, kid, senior) => {
  const { ADULT, KID, SENIOR_CITIZEN } = USER_TYPES;
  const arr = [
    {
      name: ADULT,
      count: adult,
    },
    {
      name: KID,
      count: kid,
    },
    {
      name: SENIOR_CITIZEN,
      count: senior,
    },
  ].sort((a, b) => {
    if (a.count === b.count) {
      return b.name - a.name;
    } else {
      return b.count - a.count;
    }
  });
  return arr;
};

const getUserTypeCount = (stationCollection) => {
  const { ADULT, KID, SENIOR_CITIZEN } = USER_TYPES;
  const counts = stationCollection.reduce(
    (acc, curr) => {
      return {
        ADULT: curr.userType === ADULT ? acc.ADULT + 1 : acc.ADULT,
        KID: curr.userType === KID ? acc.KID + 1 : acc.KID,
        SENIOR_CITIZEN:
          curr.userType === SENIOR_CITIZEN
            ? acc.SENIOR_CITIZEN + 1
            : acc.SENIOR_CITIZEN,
      };
    },
    { ADULT: 0, KID: 0, SENIOR_CITIZEN: 0 }
  );
  const userTypeCount = orderUserTypeCount(
    counts.ADULT,
    counts.KID,
    counts.SENIOR_CITIZEN
  );

  return userTypeCount;
};

const getSummary = (station, stationTrasactions) => {
  if (!stationTrasactions[station]) throw new Error("Invalid station");
  const stationCollection = stationTrasactions[station];
  const totalCollection = stationCollection.reduce(
    (acc, curr) => acc + curr.amountPaid + curr.serviceCharge,
    0
  );
  const totalDiscount = stationCollection.reduce(
    (acc, curr) => acc + curr.discount,
    0
  );
  const userTypeCount = getUserTypeCount(stationCollection);
  return {
    totalCollection,
    totalDiscount,
    userTypeCount,
  };
};

const printStationSummary = (station, summary) => {
  const { totalCollection, totalDiscount, userTypeCount } = summary;
  console.log(
    `TOTAL_COLLECTION ${station} ${totalCollection} ${totalDiscount}`
  );
  console.log(`PASSENGER_TYPE_SUMMARY`);
  userTypeCount.forEach((user) => {
    if (user.count) console.log(`${user.name} ${user.count}`);
  });
};

module.exports = {
  getSummary,
  printStationSummary,
};
