function [C, sigma] = dataset3Params(X, y, Xval, yval)
%DATASET3PARAMS returns your choice of C and sigma for Part 3 of the exercise
%where you select the optimal (C, sigma) learning parameters to use for SVM
%with RBF kernel
%   [C, sigma] = DATASET3PARAMS(X, y, Xval, yval) returns your choice of C and 
%   sigma. You should complete this function to return the optimal C and 
%   sigma based on a cross-validation set.
%

% You need to return the following variables correctly.
C = 1;
sigma = 0.3;

paraSize = 10;
cList = zeros(1, paraSize);
sigmaList = zeros(1, paraSize);
multStep = 3;

cList(1) = .01;
sigmaList(1) = .01;
for i = 2:paraSize
    cList(i) = cList(i-1) * multStep;
    sigmaList(i) = sigmaList(i-1) * multStep;
end
% ====================== YOUR CODE HERE ======================
% Instructions: Fill in this function to return the optimal C and sigma
%               learning parameters found using the cross validation set.
%               You can use svmPredict to predict the labels on the cross
%               validation set. For example, 
%                   predictions = svmPredict(model, Xval);
%               will return the predictions on the cross validation set.
%
%  Note: You can compute the prediction error using 
%        mean(double(predictions ~= yval))
%
errorMatrix = zeros(paraSize);
for i = 1:paraSize
    for j = 1:paraSize
        model= svmTrain(X, y, cList(i), @(x1, x2) gaussianKernel(x1, x2, sigmaList(j))); 
        predictions = svmPredict(model, Xval);
        errorMatrix(i,j) = mean(double(predictions ~= yval));
    end
end


minMatrix = min(errorMatrix(:));
[row,col] = find(errorMatrix==minMatrix);

C = cList(row(1));
sigma = sigmaList(col(1));

% =========================================================================

end
