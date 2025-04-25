const express = require('express');
const router = express.Router();
const financialAidController = require('../controllers/financialAidController');
const { jwtCheck, requireRole } = require('../middlewares/roles');

// Ajoute jwtCheck globalement si besoin
router.use(jwtCheck);
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
router.post('/', financialAidController.createRequest);
router.get('/', financialAidController.getAllRequests);

router.get('/:id', financialAidController.getRequestById);
router.put('/:id/status', financialAidController.updateRequestStatus);
router.put('/:id', financialAidController.updateRequest);
router.delete('/:id', financialAidController.deleteRequest);

module.exports = router;
