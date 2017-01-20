function [X_norm, mu, sigma] = featureNormalize(X_norm)
%FEATURENORMALIZE Normalizes the features in X 
%   FEATURENORMALIZE(X) returns a normalized version of X where
%   the mean value of each feature is 0 and the standard deviation
%   is 1. This is often a good preprocessing step to do when
%   working with learning algorithms.

% You need to set these values correctly
X_norm = X_norm;
n = size(X_norm,2); %Number of columns AKA features
mu = zeros(1, n);
sigma = zeros(1, n);

% ====================== YOUR CODE HERE ======================
% Instructions: First, for each feature dimension, compute the mean
%               of the feature and subtract it from the dataset,
%               storing the mean value in mu. Next, compute the 
%               standard deviation of each feature and divide
%               each feature by it's standard deviation, storing
%               the standard deviation in sigma. 
%
%               Note that X is a matrix where each column is a 
%               feature and each row is an example. You need 
%               to perform the normalization separately for 
%               each feature. 
%
% Hint: You might find the 'mean' and 'std' functions useful.
%       

mu = mean(X_norm);
sigma = std(X_norm);
for j=1:n
    %Note mu(j) and sigma(j) are scalars
    X_norm(:,j) = X_norm(:,j) - mu(j); %Subtract the mean of the jth feature
    X_norm(:,j) = X_norm(:,j) / sigma(j); %Divide by standard deviation
end










% ============================================================

end
