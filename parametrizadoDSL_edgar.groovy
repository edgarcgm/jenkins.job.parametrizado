job('ejemplo2_job_DSL'){
  description('Job DSL de ejmplo con Jenkins')
  scm{
    git('https://github.com/edgarcgm/jenkins.job.parametrizado.git', 'main') { node ->
      node / gitConfigName('edgarcgm')
      node / gitConfigEmail('cortsedgar@gmail.com')
    }
  }
  parameters{
    stringParam('nombre', defaultValue = 'Ergaru', description = 'Parametro de cadena para Job Booleano')
    choiceParam('planeta', ['Mercurio', 'Venus', 'Tierra', 'Marte'])
    booleanParam('agente', false)
  }
  triggers{
    cron('H/7 * * * *')
  }
  steps{
    shell("bash jobscript.sh")
  }
  publishers{
    slackNotifier {
      notifyAborted(true)
      notifyEveryFailure(true)
      notifyNotBuilt(false)
      notifyUnstable(false)
      notifyBackToNormal(true)
      notifySuccess(true)
      notifyRepeatedFailure(false)
      startNotification(false)
      includeTestSummary(false)
      includeCustomMessage(false)
      customMessage(null)
      sendAs(null)
      commitInfoChoice('NONE')
      teamDomain(null)
      authToken(null)
    }
  }
}
