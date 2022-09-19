# Road Network Efficiency

## Usage

Build the base docker image  (that copies and installs the libraries).
```
docker build -t w-k-s/ada -f Dockerfile-base
```

Build the project in docker:
```
docker build -t w-k-s/rne .
docker run rne
```