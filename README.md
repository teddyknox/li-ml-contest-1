# May 2016 Kaggle Competition - Predicting Income #

```
sbt \
  -Dtraining.classpath="training-first-32000.tsv" \
  -Dtest.classpath="training-last-561-without-label.tsv" \
  -Doutput.path="output.tsv" \
  -Dhidden.layers="40,40" \
  -Dactivation.function="sigmoid" \
  -Dlearning.rate="0.1" \
  -Dregularization.param="5" \
  run
```