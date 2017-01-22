function [J, grad] = costFunctionReg(theta, X, y, lambda)
%COSTFUNCTIONREG Compute cost and gradient for logistic regression with regularization
%   J = COSTFUNCTIONREG(theta, X, y, lambda) computes the cost of using
%   theta as the parameter for regularized logistic regression and the
%   gradient of the cost w.r.t. to the parameters. 

% Initialize some useful values
m = length(y); % number of training examples
n = size(theta); %Number of features
h = zeros(m,1);
% You need to return the following variables correctly 
J = 0;
grad = zeros(n);

% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost of a particular choice of theta.
%               You should set J to the cost.
%               Compute the partial derivatives and set grad to the partial
%               derivatives of the cost w.r.t. each parameter in theta

h = sigmoid( X * theta );
regTheta = theta(2:end);
%COSTS
normalJ = -1/m * sum ( y .* log(h) + (1-y) .* log(1-h) );
regJ = lambda/(2*m) * (regTheta' * regTheta);
J = normalJ + regJ;
%GRAD
wgrad = (1/m)*X'*(h-y); % Old Grad
regGrad = lambda/m * regTheta;
wgrad(2:end) = wgrad(2:end) - regGrad;

%WHY DOES HASSAN'S WORK BUT MINE DOESN'T!
grad = (1/m)*X'*(h-y) + (lambda/m)*[0; theta(2:end)];
% =============================================================
%Difference of  1.0859e-05 wtf
difference = sum( (wgrad - grad) .^2 );
end
