const { expressjwt: jwt } = require("express-jwt");
const jwks = require("jwks-rsa");

const jwtCheck = jwt({
  secret: jwks.expressJwtSecret({
    jwksUri: 'http://localhost:8092/realms/projet/protocol/openid-connect/certs',
    cache: true,
    rateLimit: true,
    jwksRequestsPerMinute: 5,
  }),
  audience: 'account',
  issuer: 'http://localhost:8092/realms/projet',
  algorithms: ['RS256'],
});

function requireRole(requiredRole) {
    return (req, res, next) => {
      const userRoles = req.auth?.resource_access?.microservice?.roles || 
                       req.auth?.realm_access?.roles || [];
      
      if (!userRoles.includes(requiredRole)) {
        return res.status(403).json({ message: `Access denied. Requires role: ${requiredRole}` });
      }
      next();
    };
  }

module.exports = { jwtCheck, requireRole };
