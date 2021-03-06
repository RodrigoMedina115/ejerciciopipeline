pipeline {
    agent any

    tools {
        maven 'Maven'
        nodejs "NodeJs"
    }
    stages {
      stage ('Initial') {
            steps {
              sh '''
                   echo "PATH = ${PATH}"
                   echo "M2_HOME = ${M2_HOME}"
               '''
            }
        }
        stage ('Compile') {
            steps {
                 sh 'mvn clean compile -e'
            }
        }
        stage ('Test') {
            steps {
                 sh 'mvn clean test -e'
            }
        }

        stage('SonarQube analysis') {
           steps{
                script {
                    def scannerHome = tool 'SonarQube Scanner';
                    withSonarQubeEnv('Sonar Server') {
                      sh "${scannerHome}/bin/sonar-scanner -Dsonar.projectKey=ClaseDevSecOps -Dsonar.java.binaries=target/ -Dsonar.host.url=http://172.17.0.3:9000 -Dsonar.login=5e9825807a54bf0d8bf6d6574443e9af7f399e3d"

                    }
                }
           }
        }

        stage ('SCA') {
            steps {
                 sh 'mvn org.owasp:dependency-check-maven:check'
                //dependencyCheckPublisher failedNewCritical: 5, failedTotalCritical: 10, pattern: 'target/dad.xml', unstableNewCritical: 3, unstableTotalCritical: 5
            }
        }
       stage('ZAP'){
       steps{
       script{
       env.DOCKER = tool "Docker"
       env.DOCKER_EXEC = "${DOCKER}/bin/docker"
       sh '${DOCKER_EXEC} rm -f zap2'
       sh '${DOCKER_EXEC} pull owasp/zap2docker-stable'
       sh ''' ${DOCKER_EXEC} run --add-host="localhost:200.74.104.153" --rm -e LC_ALL=C.UTF-8 -e LANG=C.UTF-8 --name zap2 -u zap -p 8090:8080 -d owasp/zap2docker-stable zap.sh -daemon  -port 8080 -host 0.0.0.0 -config api.disablekey=true'''
       sh ''' ${DOCKER_EXEC} run --add-host="localhost:200.74.104.153" -/Users/rmedinfo/Desktop/Capacitacion:/zap/wrk/:rw  --rm -i owasp/zap2docker-stable zap-baseline.py -t "http://zero.webappsecurity.com/" -I -r zap-report.html -l PASS'''
       }
       }
       }
        stage('Scan Docker'){
        steps{
        script{
        env.DOCKER = tool "Docker"
        env.DOCKER_EXEC = "${DOCKER}/bin/docker"
        sh '''
        ${DOCKER_EXEC} run --rm -v
        $(pwd):/root/.cache/ aquasec/trivy 4707fe77766b
        '''
        sh '${DOCKER_EXEC} rmi aquasec/trivy'
        }
        }
        }


    }
}