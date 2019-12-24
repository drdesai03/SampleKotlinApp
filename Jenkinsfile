node {
  // Mark the code checkout 'stage'....
  stage 'Stage Checkout'

  // Checkout code from repository and update any submodules
  checkout scm
  sh 'git submodule update --init'

  stage 'Stage Build'

  //branch name from Jenkins environment variables
  echo "My branch is: ${env.BRANCH_NAME}"

  def flavor = "${env.BRANCH_NAME}"
  echo "Building flavor ${flavor}"

  if(flavor == "master") {
    sh "./gradlew clean assembleStableDebug assembleStableRelease lintStableDebug -PBUILD_NUMBER=${env.BUILD_NUMBER}"
  } else {
    sh "./gradlew clean assembleSnapshotDebug assembleSnapshotRelease lintSnapshotDebug -PBUILD_NUMBER=${env.BUILD_NUMBER}"
  }
  //build your gradle flavor, passes the current build number as a parameter to gradle
  //sh "./gradlew clean assemble${flavor}Debug lint${flavor}Debug -PBUILD_NUMBER=${env.BUILD_NUMBER}"

  stage 'Stage Archive'
  //tell Jenkins to archive the apks
  if(flavour == "master") {
    archiveArtifacts artifacts: 'app/build/outputs/apk/stable/debug/*.apk', fingerprint: true
  } else {
    archiveArtifacts artifacts: 'app/build/outputs/apk/snapshot/debug/*.apk', fingerprint: true
  }

  //stage 'Stage Upload To Fabric'
  //sh "./gradlew crashlyticsUploadDistribution${flavor}Debug  -PBUILD_NUMBER=${env.BUILD_NUMBER}"
}

// Pulls the android flavor out of the branch name the branch is prepended with /QA_
@NonCPS
def flavor(branchName) {
  def matcher = (env.BRANCH_NAME =~ /feature\/([a-z_]+))
  assert matcher.matches()
  matcher[0][1]
}