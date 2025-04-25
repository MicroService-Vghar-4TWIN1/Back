const mongoose = require('mongoose');

const financialAidSchema = new mongoose.Schema({
  studentId: String, 
  createdBy: String,
  amountRequested: {
    type: Number,
    min: 0,
  },
  reason: {
    type: String,
  },
  status: {
    type: String,
    enum: ['pending', 'approved', 'rejected'],
    default: 'pending',
  },
  dateSubmitted: {
    type: Date,
    default: Date.now,
  },
  dateReviewed: Date,
  departmentId: Number 



});

module.exports = mongoose.model('FinancialAid', financialAidSchema);
