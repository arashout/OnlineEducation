function [J, grad] = linearRegCostFunction(X, y, theta, lambda)
%LINEARREGCOSTFUNCTION Compute cost and gradient for regularized linear 
%regression with multiple variables
%   [J, grad] = LINEARREGCOSTFUNCTION(X, y, theta, lambda) computes the 
%   cost of using theta as the parameter for linear regression to fit the 
%   data points in X and y. Returns the cost in J and the gradient in grad

% Initialize some useful values
m = length(y); % number of training examples


% ====================== YOUR CODE HERE ======================
% Instructions: Compute the cost and gradient of regularized linear 
%               regression for a particular choice of theta.
%
%               You should set J to the cost and grad to the gradient.
%
h = X*theta; %Hypothesis is used alot
regTheta = theta(2:end); %Get rid of bias term
normalJ = (1/(2*m)) * dot(h - y, h - y);
regJ = lambda/(2*m) * (regTheta' * regTheta); %Don't count bias term during regularization
J = normalJ + regJ;

normalGrad = (1/m)*X'*(h-y);
regGrad = lambda/m*[0; theta(2:end)]; %Ignore the bias feature in regularization
grad = normalGrad + regGrad;



% =========================================================================

grad = grad(:);

end
