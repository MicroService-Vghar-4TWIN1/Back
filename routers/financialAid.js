const express = require('express');
const router = express.Router();
const financialAidController = require('../controllers/financialAidController');
const { jwtCheck, requireRole } = require('../middlewares/roles');

// Ajoute jwtCheck globalement si besoin
router.use(jwtCheck);

router.post('/', financialAidController.createRequest);
router.get('/', financialAidController.getAllRequests);

router.get('/:id', financialAidController.getRequestById);
router.put('/:id/status', financialAidController.updateRequestStatus);
router.put('/:id', financialAidController.updateRequest);
router.delete('/:id', financialAidController.deleteRequest);

module.exports = router;
