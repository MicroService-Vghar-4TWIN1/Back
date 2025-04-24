const FinancialAid = require("../models/financialAid");
const {
  publishToExchange,
  QUEUES,
  EXCHANGE_NAME,
} = require("../config/rabbitmq");

// Get all financial aid requests
exports.getAllRequests = async (req, res) => {
  try {
    const requests = await FinancialAid.find();
    res.status(200).json(requests);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Get a single financial aid request
exports.getRequestById = async (req, res) => {
  try {
    const request = await FinancialAid.findById(req.params.id);
    if (!request) {
      return res.status(404).json({ message: "Request not found" });
    }
    res.status(200).json(request);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Create a financial aid request (already exists in your code)
exports.createRequest = async (req, res) => {
  /* ... existing code ... */
};

// Update request (general update)
exports.updateRequest = async (req, res) => {
  try {
    const updated = await FinancialAid.findByIdAndUpdate(
      req.params.id,
      req.body,
      { new: true }
    );
    if (!updated) {
      return res.status(404).json({ message: "Request not found" });
    }
    res.status(200).json(updated);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Update request status (already exists in your code)
exports.updateRequestStatus = async (req, res) => {
  /* ... existing code ... */
};

// Delete a request
exports.deleteRequest = async (req, res) => {
  try {
    const deleted = await FinancialAid.findByIdAndDelete(req.params.id);
    if (!deleted) {
      return res.status(404).json({ message: "Request not found" });
    }
    res.status(200).json({ message: "Request deleted successfully" });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
