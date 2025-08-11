# WASABI Interview Exercise

Welcome! This is a small, standalone [Grails](https://grails.apache.org/) application created for an interview exercise.

It is a "vertical slice" of one of WASABI's applications, i.e. it contains all the layers of the full web application, but implements only a single feature/workflow.

In order to keep things simple for this exercise, some things have been replaced with a "dummy" implementation.
There are comments in the code to indicate where this has been done. You can safely ignore those areas for this exercise.

## Your Task

Your task is to review and **critique the service and web tiers in this repository**, which can be found respectively in:

* `grails-app/services/edu/stsci/proper/ProposalService.groovy`
* `grails-app/controllers/edu/stsci/proper/ProposalController.groovy`

Some guidance:

* **Any and all criticism is fair game,** from "big picture" down to the details.
* **Don't worry about offending us**—this is legacy code which has essentially not been touched by anyone currently on the team.
* **There are no wrong answers:** Our goal is to understand how well your values and opinions may already align with our own.
* **Ask questions** if you see something you're not sure you understand. We don't necessarily expect you to be familiar with the technologies used in this project.

Lastly, while fixing the problems you identify is generally outside the scope of this exercise, we may ask you to make some edits to help give us a more concrete understanding of your ideas.

## Running the Application (Optional)

**You do not necessarily need to run the application for this exercise.**
However, the project is runnable, and you are welcome to do so if you wish.

As long as you have a JDK 8+ installed and in your `PATH`, all you should need to do to start the application is:

```bash
./gradlew bootRun
```

(Or, if you are on a Windows machine, you may need to use `gradlew.bat` instead.)

The first time you run this command, you may have to wait a few minutes as it automatically downloads the appropriate
version of Gradle and all of the project's dependencies.
Once those things are downloaded, compiling and starting the application takes only a few moments.

When startup is complete, the application will be available at `http://localhost:8080/`.
Opening that URL in your browser will automatically redirect you to the relevant feature.

