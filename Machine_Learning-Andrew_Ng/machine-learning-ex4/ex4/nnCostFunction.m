function [J, grad] = nnCostFunction(nn_params, ...
    input_layer_size, ...
    hidden_layer_size, ...
    num_labels, ...
    X, y, lambda)
%NNCOSTFUNCTION Implements the neural network cost function for a two layer
%neural network which performs classification
%   [J grad] = NNCOSTFUNCTON(nn_params, hidden_layer_size, num_labels, ...
%   X, y, lambda) computes the cost and gradient of the neural network. The
%   parameters for the neural network are "unrolled" into the vector
%   nn_params and need to be converted back into the weight matrices.
%
%   The returned parameter grad should be a "unrolled" vector of the
%   partial derivatives of the neural network.
%

% Reshape nn_params back into the parameters Theta1 and Theta2, the weight matrices
% for our 2 layer neural network
Theta1 = reshape(nn_params(1:hidden_layer_size * (input_layer_size + 1)), ...
    hidden_layer_size, (input_layer_size + 1));

Theta2 = reshape(nn_params((1 + (hidden_layer_size * (input_layer_size + 1))):end), ...
    num_labels, (hidden_layer_size + 1));

% Setup some useful variables
m = size(X, 1);

% You need to return the following variables correctly
J = 0;
Theta1_grad = zeros(size(Theta1));
Theta2_grad = zeros(size(Theta2));

% ====================== YOUR CODE HERE ======================
% Instructions: You should complete the code by working through the
%               following parts.
%
% Part 1: Feedforward the neural network and return the cost in the
%         variable J. After implementing Part 1, you can verify that your
%         cost function computation is correct by verifying the cost
%         computed in ex4.m
%
% Part 2: Implement the backpropagation algorithm to compute the gradients
%         Theta1_grad and Theta2_grad. You should return the partial derivatives of
%         the cost function with respect to Theta1 and Theta2 in Theta1_grad and
%         Theta2_grad, respectively. After implementing Part 2, you can check
%         that your implementation is correct by running checkNNGradients
%
%         Note: The vector y passed into the function is a vector of labels
%               containing values from 1..K. You need to map this vector into a
%               binary vector of 1's and 0's to be used with the neural network
%               cost function.
%
%         Hint: We recommend implementing backpropagation using a for-loop
%               over the training examples if you are implementing it for the
%               first time.
%
% Part 3: Implement regularization with the cost function and gradients.
%
%         Hint: You can implement this around the code for
%               backpropagation. That is, you can compute the gradients for
%               the regularization separately and then add them to Theta1_grad
%               and Theta2_grad from Part 2.
%

%%%%%%%%%%%%%%%%%%%%
%FORWARD PROPOGATION
%%%%%%%%%%%%%%%%%%%%

%Add bias to X
a1 = [ones(m, 1), X];
%Compute z2 by multiplying inputs by weights of theta1 and summing
z2 = a1 * Theta1';
%Get a2 by applying sigmoid and adding bias
a2 = [ones(m,1) ,sigmoid(z2)];
%Compute z3 by multiplying inputs by weights of theta2 and summing
z3 = a2 * Theta2';
%Get a2 by applying sigmoid = output
%Basically at this point I have a hypothesis matrix with 5000x10
%5000 training samples and 10 labels
a3 = sigmoid(z3);
h = a3;

%%%%%%%%%%%%%%%%%%%%
%COST
%%%%%%%%%%%%%%%%%%%%

%Transform y values into vectors, since that's what a3 looks like
Y = zeros(m, num_labels);
for i = 1: max(y)
    Y(:,i) = (y==i);
end

normalJ = 0;
for i = 1:m
    for k = 1:num_labels
        normalJ = normalJ + Y(i,k) * log(h(i,k)) + (1 - Y(i,k))*log(1-h(i,k));
    end
end

%Remember to exclude bias units which are in column 1
%First sum all rows, then sum resulting column vector
reg1 = sum (sum( Theta1(:,2:end) .^ 2, 1) ); %Reg of matrix Theta1
reg2 = sum (sum( Theta2(:,2:end) .^ 2, 1) ); %Reg of metrix Theta2
reg = lambda/(2*m) * (reg1 + reg2);
J = -normalJ/m + reg;

%%%%%%%%%%%%%%%%%%%%
%BACK PROPAGATION- GRADIENTS
%%%%%%%%%%%%%%%%%%%%
delta3 = a3 - Y;
%Need to get rid of bias and make transverse to make dimesions work !
tempDelta2 = Theta2' * delta3';
tempDelta2 = tempDelta2(2:end,:)';
delta2 = tempDelta2 .* sigmoidGradient(z2);

Theta2_grad = (1/m)*delta3'*a2;
Theta1_grad = (1/m)*delta2'*a1;








% -------------------------------------------------------------

% =========================================================================

% Unroll gradients
grad = [Theta1_grad(:) ; Theta2_grad(:)];


end
