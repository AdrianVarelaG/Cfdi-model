 # CFDI MODEL
 
## Usage

### install local
`docker run -it --rm -v "$PWD":/usr/src/mymaven -v "$HOME/.m2":/root/.m2 -w /usr/src/mymaven maven:3.6.1-jdk-8-alpine mvn clean install`