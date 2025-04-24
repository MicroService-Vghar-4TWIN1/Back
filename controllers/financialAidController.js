const FinancialAid = require("../models/financialAid");
const {
  publishToExchange,
  QUEUES,
  EXCHANGE_NAME,
} = require("../config/rabbitmq");

// Create a financial aid request
exports.createRequest = async (req, res) => {
  try {
    const newRequest = new FinancialAid(req.body);
    const saved = await newRequest.save();

    // Publish event about new request
    await publishToExchange(EXCHANGE_NAME, "request.new", {
      type: "FINANCIAL_AID_REQUEST_CREATED",
      data: saved,
    });

    res.status(201).json(saved);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};

// Update request status (approve or reject)
exports.updateRequestStatus = async (req, res) => {
  const { status, reviewerId, comments } = req.body;

  if (!["approved", "rejected"].includes(status)) {
    return res.status(400).json({ message: "Invalid status" });
  }

  try {
    const updated = await FinancialAid.findByIdAndUpdate(
      req.params.id,
      {
        status,
        reviewerId,
        comments,
        dateReviewed: new Date(),
      },
      { new: true }
    );

    if (!updated) return res.status(404).json({ message: "Request not found" });

    // Publish status update event
    await publishToExchange(EXCHANGE_NAME, "status.update", {
      type: "FINANCIAL_AID_STATUS_UPDATED",
      data: updated,
    });

    res.status(200).json(updated);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Other controller methods remain the same...
