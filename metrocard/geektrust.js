const fs = require("fs");

const { setCardBalance } = require("./src/models/metroCardBalance");
const {
  makeTransaction,
  printSummary,
} = require("./src/models/stationTransaction");

const filename = process.argv[2];

fs.readFile(filename, "utf8", (err, data) => {
  if (err) throw err;
  const inputLines = data.toString().split("\n");
  // Add your code here to process input commands
  inputLines.forEach((line) => {
    const arguments = line.trim().split(" ");
    const command = arguments.shift();
    generateOutput(command, [...arguments]);
  });
});

const generateOutput = (command, args) => {
  switch (command) {
    case "BALANCE": {
      const [cardNumber, balance] = args;
      if (!cardNumber) throw new Error("Invalid card number");
      setCardBalance(cardNumber, balance);
      return;
    }
    case "CHECK_IN": {
      const [cardId, userType, station, destination] = args;
      return makeTransaction(cardId, userType, station);
    }
    case "PRINT_SUMMARY": {
      printSummary();
      return;
    }

    default: {
      console.log("Unknown command");
    }
  }
};
