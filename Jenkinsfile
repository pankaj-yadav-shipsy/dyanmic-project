pipeline {
    agent {label 'android'}
    environment {
        PATH = "/usr/local/opt/ruby/bin:/Library/Frameworks/Python.framework/Versions/3.10/bin:/usr/local/bin:/usr/bin:/bin:/usr/sbin:/sbin:/Library/Apple/usr/bin:/Users/pankaj/.gem/ruby/2.6.0/bin:/Users/pankaj/Library/Android/sdk/platform-tools/"
        FOLDER_NAME= "${GIT_BRANCH.split("/")[1]}"

    }
//     triggers {
//         GenericTrigger(
//             genericVariables: [
//                 [key: 'target_branch', value: '\$.pullrequest.destination'],
//             ],
//             causeString: 'Triggered on $target_branch',
//             token: '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',
//             printContributedVariables: true,
//             printPostContent: true,
//             silentResponse: false,
//             regexpFilterText: '$target_branch',
//             regexpFilterExpression: 'stage'
//         )
//     }
    stages {
         stage("Build APK") {
            steps {
          //  echo printPostContent
             sh './gradlew assembleDebug'
            }
        }
//           stage("upload to S3"){
//             steps{
//                withAWS(region:'us-west-2',credentials:'pradex-test') {
//                  echo 'Pulling...' + "${FOLDER_NAME}"
//                s3Upload(bucket:"pradex-test", workingDir:'/Users/pankaj/.jenkins/workspace/emand-mulitpipline_feature_ci_cd/app/build/outputs/apk', includePathPattern:'** /* *//*', excludePathPattern:'** /* *//*.svg,** /* *//*.json', path:"${FOLDER_NAME}");
//
//               }
//             }
        }
    }
}