First attempt at test set with these parameters got us 84.36% accuracy:
```
sbt \
  -Dtraining.classpath="training-first-26049.tsv" \
  -Dtest.classpath="training-last-6512.tsv" \
  -Doutput.path="test_predictions.txt" \
  -Dhidden.layers="20,20" \
  -Dactivation.function="relu" \
  -Dlearning.rate="0.01" \
  -Dregularization.param="0.001" \
  -Depochs="30" \
  -Dbatch.size="30" \
  -Densemble.size="15" \
  -Dbagging.fraction="0.6" \
  -Dboosting.strategy="AverageSelection" \
  run
```
