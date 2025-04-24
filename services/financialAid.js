// controllers/bookController.js
const { Book } = require("../models/book");

const searchAvailableBooks = async () => {
  const books = await Book.find({ available: true });
  return books;
};

// Improved version with proper Express handler structure
exports.searchAvailableBooks = async (req, res) => {
  try {
    const books = await Book.find({ available: true });
    res.status(200).json(books);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
