# Sprint 4 - t13 - Spaghetti Coders

## Goal

### Interactive Maps and Shorter Trips!
### Sprint Leader: *John Miller*

## Definition of Done

* Sprint Review and Restrospectives completed (sprint4.md).
* Version in pom.xml should be `<version>4.0.0</version>`.
* Increment deployed for demo and testing as server-4.0.jar on the production server.
* Increment release `v4.0` created on GitHub with appropriate version number and name.
* Epics and Tasks updated in Zenhub.


## Policies

#### Test Driven Development
* Write method headers, javadoc, unit tests, and code in that order for all methods/functions.
* Unit tests are fully automated.
* Code coverage is at least 50%, 70% preferred.
#### Clean Code
* Code adheres to Google style guides for Java and JavaScript.
* Code Climate maintainability of A or B.
#### Configuration Management
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* All commits with more than 1 line of change include a task/issue number.
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
#### Continuous Integration / Delivery
* Travis successfully builds and tests all pull requests for master branch.
* All Java dependencies in pom.xml.  Do not load external libraries in your repo. 
* All pull requests are deployed on the development server.
* The development server is never broken.  If broken, it is fixed immediately.


## Plan

Our main goal this sprint is to fix bugs found from the sprint 3 demo. Since some of our calculations for short trips are incorrect, and there are some bugs with what is returned in TFFI, we want to focus on fixing these issues before moving on. 

In addition, we want to clean our client's layout, both in code and what is rendered on the page. We plan to dedicate time to cleaning the client side's code, and making the webpage layout more user-friendly. 

Finally, we want to implement world-wide trips, shorter trips, and more options for the user to utilize, described from sprint 4's epics.

#### Diagrams

<img src="https://github.com/csu18fa314/t13/blob/master/team/leejr0/Sprint4Layout.jpg" width="450">
*This is the new layout planned for the website by the end of Sprint 4. The layout will have the map now rendered at the bottom of the page, and the choose file and change server button closer to the top of the page. We also want to implement drop down buttons to hide different options to reduce the amount of space on the page.*

<img src="https://user-images.githubusercontent.com/42558356/47400444-6b969980-d6fa-11e8-86cc-70fd6958b691.jpg" width=400px>
*This diagram illustrates new sprint 4 component hierarchy our team will have. The focus of this sprint(at least for the client side) is to minimize amount of components instantiated per component, to decrease smelly code practices.*

<img src="https://github.com/csu18fa314/t13/blob/master/images/ServerDiagram.png" width=400px>
*This diagram illustrates the current state of our implementation of the server requests by outlining class usages and their places in the request flow.*

Epics planned for this sprint.

* TripCo: All code shall be clean! : <br>
-Adhere to the Google style guide for Java and JavaScript.
-Write maintainable code.
-Time to read the book if you haven't started.
-More to come!
-You may amend your sprint#.md and README.md with policies as needed to reflect specifics you would like to focus on for the sprint and the future.
* TripCo: All code must be tested. : <br>
-minimum 50% coverage, preferred 70% coverage
* User: I want to choose what information is displayed in the itinerary and map. : <br>
-allow additional attribute/value pairs to be captured for destinations whether they are entered manually or obtained from a database.
-allow the user to select attributes to display in the itinerary, including the latitude and longitude.
* User: I'd like even shorter trips. : <br>
-Use 2-opt to improve the nearest neighbor tours.
* User: I want to plan trips worldwide. : <br>
-Use a new database that contains a list of destinations worldwide.
-Allow the user to filter search requests.
* User: I want to view my trip in other tools. : <br>
-Use a mapping tool to draw the map and allow the user to zoom and pan.

Extra epics planned for the future:
* User: I want the shortest trips possible: <br>
-Use 3-opt to improve the nearest neighbor trips.
* User: I want trip planning to be fast.: <br>
-Use concurrency to improve trip planning performance so there is less of a delay for the user.


## Metrics

| Statistic | Planned | Completed |
| --- | ---: | ---: |
| Epics | 4 for sprint 4, 2 left from sprint 3 | 3 |
| Tasks |  45   | 36 | 
| Story Points |  67  | 51 | 

Although we were able to complete most of the tasks that we created this sprint, since we continued to add tasks and story points as our understanding of the epics increased we ultimately ended up with a number of uncompleted tasks.

*Enter the `# Planned` at the beginning of the sprint.  Include a discussion of planning decisions based on the planned number of story points versus how many were completed in previous sprints.* <br>
We worked to increase the number of story points that we had for this sprint's plan. In the previous sprints, our team had trouble achieving a 50% complete tasks by the middle of the sprint, due to planning errors, and the amount of work we need to get done. <br> 
*Edit 11/2/2018* Due to more tasks and issues being created throughout the sprints, this sprint we have changed a few things part of the way through. For example, we are no longer creating tasks for updating the sprint's .md file. Because of this, our team may have less issues completing the required amount of story point by the end of the sprint. 

*Enter the `# Completed` at the end of the sprint.  Include a discussion about any difference in the number planned versus completed tasks and story points.*


## Scrums

| Date | Tasks closed  | Tasks in progress | Impediments |
| :--- | :--- | :--- | :--- |
| 10/24/2018 | #259, #301, #302, #303 | #294, #296, #300, #306, #308, #309, #316, #325 | *none* | 
| 10/26/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303 | #300, #306, #308, #309 | *none* |
| 10/29/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303, #296, #325, #294, #330, #328, #277, #300, #308, #269, #309, #313, #319 | #298, #306, #334 | *none* |
| 10/31/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303, #296, #325, #294, #330, #328, #277, #300, #308, #269, #309, #313, #319, #334, #348 | #320, #298, #346, #352, #317, #321 | *none* |
| 11/2/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303, #296, #325, #294, #330, #328, #277, #300, #308, #269, #309, #313, #319, #334, #348, #320, #298, #346, #352, #317, #321 | #299, #355 | *none* |
| 11/5/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303, #296, #325, #294, #330, #328, #277, #300, #308, #269, #309, #313, #319, #334, #348, #320, #298, #346, #352, #317, #321, #351, #360, #361, #347, #355 | #299, #314 | *none* | 
| 11/7/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303, #296, #325, #294, #330, #328, #277, #300, #308, #269, #309, #313, #319, #334, #348, #320, #298, #346, #352, #317, #321, #351, #360, #361, #347, #355, #381 | #365, #299, #314, #318, #369, #312, #323, #379 | *none* |
| 11/8/2018 | #277, #328, #330, #294, #296, #325, #259, #301, #302, #303, #296, #325, #294, #330, #328, #277, #300, #308, #269, #309, #313, #319, #334, #348, #320, #298, #346, #352, #317, #321, #351, #360, #361, #347, #355, #381, #365, #299, #314, #318, #369, #312, #323, #379, #386 | *none* | *none* |

*Add a new row for the scrum session after each lecture. *

## Review

This sprint went fairly well, as our team completed quite a few epics and worked to catch up in code maintainability and test coverage from the previous sprint. While not everything went as planned or perfectly, we brought our skills together to develop a presentable product by the end of the sprint.

#### Completed Epics in Sprint Backlog 

*Describe the solution based on the completed epics and list the epics below.*

* User: I want to plan trips worldwide: We were able to complete this epic by implementing the required filters and expanding our database to be worldwide.
* User: I'd like even shorter trips: We were able to implement 2-opt to impove our nearest neighbor tours. We are currently working on shortening our algorithm's runtime going forward.
* User: I want to choose what information is displayed in the itinerary and map: We were able to allow the user to hide and show the different attributes in the itinerary and also to allow for the capture of additional attribute/value pairs from the database.

#### Incomplete Epics in Sprint Backlog 

* User: I want to view my trip in other tools: We were not able to implement any of the kml-related features this sprint and as such we currently only support svg.
* TripCo: All code shall be clean!: Since we have marked this as an ongoing epic we are endeavoring to maintain our standard of clean code.
* TripCo: All code must be tested: Although we made great improvements to our code coverage this sprint, our current test coverage sits at 46%, which is slightly lower than the cutoff of 50% coverage.

## I want to view my trip in other tools: 
Since this epic had the lowest priority out of all the other epics our team was assigned this sprint, we simply did not have enough time resource to implement the issues associated with this epic.


#### What Went Well

We better used our time this sprint, with tasks being done more incrementally than last sprint. There still were some "big bangs" in the diagram, but we worked on different tasks for longer than just the night that tasks were closed.

In addition, our team had better teamwork this sprint. Some of the larger epics and tasks, we split into pairs to complete, such as the efficiency algorithms tasks. Overall, we communicated better with each other and spoke up more in Piazza and class when we didn't understand a concept or a goal.

We completed tasks to the best of our abilities, and prioritized some tasks over others, improving our team's time-management skills. 

#### Problems Encountered and Resolutions

There were a few times when the code on our master in Github was broken, and there were several hidden bugs that were only exposed with time that we had to fix. This hindered our ability to get as much done as our team could have this sprint. In addition, we had some group members give less time due to other responsibilities such as exams in other classes and traveling. This led to some issues in teamwork when not all group members could be present for meetings. 

To fix the code on master, we had to be quick and supportive when bugs were found on master, and patient enough to find and fix the bugs that were found.

We had to rely on each other to resolve time constraints of different team members. Some tasks had to be "handed-off" to other team members in order to have them finished by the check and the final deploy. 

## Retrospective
During this sprint, our team has improved task closing significantly since last sprint. The burndown chart illustrated a much more consistent and fluid work flow(in other words, not as many "big bangs") this sprint. Teamwork between groupmates within the group as well as other groups has improved significantly(ever since the interop assignent). 
#### What we changed this sprint

One of the big chages we made this sprint was testing. During sprint 4 we better applied the ideas of test driven development, and ran ran more tests localy instead of just relying on travis to test our changes. Another change, was an improvent in incremental development. During this sprint we saw less big bangs in the burn-down chart.  

#### What we did well

Some things that we did well were; increasing test coverage, improving our interoperability, and working in increments. During this sprint we were able to up out test coverage to almost 50%. We were also better about checking our site against the other teams sites though out the sprint, to gauge our progress. Lastly we were able to close isues more consistently throught the sprint, instead of just having a few big bangs.

#### What we need to work on

One thing we can work on is deploying our server more often. Over the course of the sprint we only deployed a few times. On top of that, we can also continue to improve our test coverage, and incremental development. 

#### What we will change next sprint 

The main change that we would like to make next sprint is to deploy more often. The goal will be to deploy a new version of our server every time we make a noticeable change to the UI or the server. Our other goal will be to increase our test coverage and be more consistent with closing issues. 
