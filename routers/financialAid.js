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
  const { requestDepartments } = require('../rabbit/rpcClient');
  router.get('/departments', async (req, res) => {
    try {
      const departments = await requestDepartments(); // from RabbitMQ service
      res.json(departments);
    } catch (error) {
      console.error(error);
      res.status(500).send('Error retrieving departments');
    }
  });
router.post('/', createRequest);

router.get('/', getAllRequests);

router.get('/:id', getRequestById);

router.put('/:id/status', updateRequestStatus);
router.put('/:id', updateRequest);

router.delete('/:id', deleteRequest);

module.exports = router;
