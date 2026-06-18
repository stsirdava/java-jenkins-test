pipeline {

agent any

options {
    buildDiscarder(
        logRotator(
            daysToKeepStr: '5',
            numToKeepStr: '10'
        )
    )
}

parameters {
    choice(
        name: 'ENV',
        choices: ['dev', 'qa', 'staging', 'prod'],
        description: 'Target environment'
    )
}

triggers {
    githubPush()
    cron('30 13,18 * 3 0,2')
}

stages {

    stage('Build') {
        steps {
            echo "Building for ${params.ENV}"
            bat 'mvn clean compile'

            writeFile(
                file: 'build-info.txt',
                text: """
Build Number: ${env.BUILD_NUMBER}
Environment: ${params.ENV}
"""
)
}
}

    stage('Test') {

        parallel {

            stage('UI Tests') {
                steps {
                    bat 'mvn test -Dgroups=ui'
                }
            }

            stage('API Tests') {
                steps {
                    bat 'mvn test -Dgroups=api'
                }
            }
        }
    }

    stage('Archive Artifacts') {
        steps {

            archiveArtifacts(
                artifacts: 'build-info.txt',
                fingerprint: true
            )
        }
    }

    stage('Generate HTML Report') {
        steps {

            writeFile(
                file: 'custom-report/index.html',
                text: '''
                <html>
                    <body>
                        Jenkins Exam Demo Report
                    </body>
                </html>
                '''
            )
        }
    }
}

post {

    success {
        echo 'Build successfully finished'
    }

    unsuccessful {

        writeFile(
            file: 'log.txt',
            text: """
Build Result: ${currentBuild.currentResult}
Build Number: ${env.BUILD_NUMBER}
Job Name: ${env.JOB_NAME}
"""
)

        archiveArtifacts artifacts: 'log.txt'
    }

    always {

        junit '**/target/surefire-reports/*.xml'

        allure(
            results: [
                [path: 'target/allure-results']
            ]
        )

        publishHTML([
            allowMissing: false,
            alwaysLinkToLastBuild: true,
            keepAll: true,
            reportDir: 'custom-report',
            reportFiles: 'index.html',
            reportName: 'Demo HTML Report'
        ])

        cleanWs()
    }
}
}
