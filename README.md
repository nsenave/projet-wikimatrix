# Wikipedia Matrix

Extracting Wikipedia tables into CSV files. Once the git is cloned:
```
cd wikimatrix 
mvn test
``` 

We get a "OutOfMemory Java heap space" when we try to do all the tests, so I would recommand you to try first:
```
mvn -Dtest=SingleTest test
```
which will launch the extractors only on this page:
https://en.wikipedia.org/wiki/Comparison_of_Canon_EOS_digital_cameras 

We give 300+ Wikipedia URLs and the challenge is to:
 * integrate the extractors' code (HTML)
 * extract as many relevant tables as possible 
 * serialize the results into CSV files (within `output1/` and `output2/`, a folder for each of the two extractors) 
 

## Notes

Java Jvm 1.8 minimum is required

