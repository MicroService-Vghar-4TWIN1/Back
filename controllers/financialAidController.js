const FinancialAid = require('../models/financialAid');
const { requestDepartments } = require('../rabbit/rpcClient');

// Create a financial aid request
exports.createRequest = async (req, res) => {
  try {
    const { amountRequested, reason, departmentId } = req.body;

    if (!departmentId) return res.status(400).json({ error: 'Department is required.' });

    const newRequest = new FinancialAid({ amountRequested, reason, departmentId });
    const saved = await newRequest.save();

    res.status(201).json(saved);
  } catch (error) {
    res.status(400).json({ error: error.message });
  }
};
// Update a request without changing the status
exports.updateRequest = async (req, res) => {
    try {
      const { amountRequested, reason, dateSubmitted, dateReviewed } = req.body;
  
      const updated = await FinancialAid.findByIdAndUpdate(
        req.params.id,
        { amountRequested, reason, dateSubmitted, dateReviewed },
        { new: true }
      );
  
      if (!updated) return res.status(404).json({ message: 'Request not found' });
  
      res.status(200).json(updated);
    } catch (error) {
      res.status(500).json({ error: error.message });
    }
  };
  

// Get all requests
exports.getAllRequests = async (req, res) => {
  try {
    const requests = await FinancialAid.find();
    res.status(200).json(requests);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Get a specific request by ID
exports.getRequestById = async (req, res) => {
  try {
    const request = await FinancialAid.findById(req.params.id);
    if (!request) return res.status(404).json({ message: 'Not found' });
    res.status(200).json(request);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Update request status (approve or reject)
exports.updateRequestStatus = async (req, res) => {
  const { status, reviewerId, comments } = req.body;

  if (!['approved', 'rejected'].includes(status)) {
    return res.status(400).json({ message: 'Invalid status' });
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

    if (!updated) return res.status(404).json({ message: 'Request not found' });

    res.status(200).json(updated);
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};

// Delete a request
exports.deleteRequest = async (req, res) => {
  try {
    const deleted = await FinancialAid.findByIdAndDelete(req.params.id);
    if (!deleted) return res.status(404).json({ message: 'Request not found' });
    res.status(200).json({ message: 'Request deleted' });
  } catch (error) {
    res.status(500).json({ error: error.message });
  }
};
