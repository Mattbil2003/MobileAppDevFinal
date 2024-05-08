# MobileAppDevFinal

**For Professor Howard**
Hi. This is my app Acrane Ambitions.
It is a scheduling app that turns boring tasks into fantasy themed ones.

I was able to complete most of the functionalities I promised in the proposal. I will admit the styling fell short quite a bit, and i wasn't able to 
complete individual pages for each of the "quests". However, I am still happy with what I was able to accomplish though and I worked really hard on
it.

Also, the code for the API that I have hosted on Azure is in here too.
**For Professor Howard**


USING THE APP

You have 3 pages you can navigate between. 
    - The home page
    - The Create Task/Quest Page
    - The Profile Page

The Create Task/Quest Page
    This page is for creating quests. You input the task title and descriptions in their respective fields. Then you put in a time using a clock feature.
    Once You are done with that you can hit the create Task button. It will take time for the API request to come in, and navigating to different pages breaks the app, so I made them unclickable. 
    P.S. Sometimes the Api doesn't work becuase I ask chatgpt to give a json output. Once you see the task failed toast, you can click create task again.

The Home Page:
    This page is a recycler view for all of the quests you currently have. Each quest will have 3 readable attributes. A fancy title, a fantasy description, and the deadline.
    There are also two buttons. One for completing the quest and one for failing. They both get rid of the quest, but only one rewards you!

The Profile Page:
    Completing a quest gives you xp and gold, this is where you can see that. Also, the xp and gold gained are considering the type of quest you are doing. 
    Lastly, there is a reset quests button that deletes all the current quests on your home page.

Thank you for checking it out!