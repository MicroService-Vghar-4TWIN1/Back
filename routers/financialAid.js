const express = require('express');
const router = express.Router();
const financialAidController = require('../controllers/financialAidController');
const {
    createRequest,
    getAllRequests,
    getRequestById,
    updateRequestStatus,
    deleteRequest,
    updateRequest
  } = require("../controllers/financialAidController");
  
router.post('/', createRequest);

router.get('/', getAllRequests);

router.get('/:id', getRequestById);

router.put('/:id/status', updateRequestStatus);
router.put('/:id', updateRequest);

router.delete('/:id', deleteRequest);

module.exports = router;
