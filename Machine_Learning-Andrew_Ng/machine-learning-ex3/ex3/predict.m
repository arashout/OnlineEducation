function p = predict(Theta1, Theta2, X)
%PREDICT Predict the label of an input given a trained neural network
%   p = PREDICT(Theta1, Theta2, X) outputs the predicted label of X given the
%   trained weights of a neural network (Theta1, Theta2)

% Useful values
m = size(X, 1);
num_labels = size(Theta2, 1);

% You need to return the following variables correctly 
p = zeros(size(X, 1), 1);

% ====================== YOUR CODE HERE ======================
% Instructions: Complete the following code to make predictions using
%               your learned neural network. You should set p to a 
%               vector containing labels between 1 to num_labels.
%
% Hint: The max function might come in useful. In particular, the max
%       function can also return the index of the max element, for more
%       information see 'help max'. If your examples are in rows, then, you
%       can use max(A, [], 2) to obtain the max for each row.
%

%Add bias to X
a1 = [ones(m, 1), X];
%Compute z2 by multiplying inputs by weights of theta1 and summing
z2 = a1 * Theta1';
%Get a2 by applying sigmoid and adding bias
a2 = [ones(m,1) ,sigmoid(z2)];
%Compute z3 by multiplying inputs by weights of theta2 and summing
z3 = a2 * Theta2';
%Get a2 by applying sigmoid = output
a3 = sigmoid(z3);
%Get predictions by finding position of max element in each row -> Each
%column represents class that fits best
[~, p] = max(a3, [], 2);








% =========================================================================


end
