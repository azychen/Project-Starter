# BALL PIT

![shapes](https://i.dailymail.co.uk/1s/2019/04/26/15/12753288-6963675-image-a-8_1556290394933.jpg)
## Anton Chen 

> _**What** does this application do?_

This project is able to simulate a 2 dimensional physical environment with object collisions under
the effects of gravity and friction. In addition, objects may be 
created by a user, with physical
properties able to be specified by the user.

> _**Who** will use it?_

This project is primarily intended to be used by a younger demographic of students in an 
educational setting, where they can "get their hands dirty" and toy around with various physical
phenomena. However, this project has the aim of being more than a toy, with intended functionality 
including calculations and values, simulating an experimental setting.     

> _**Why** did I decide on this project idea?_

The notion that mathematics, a purely man-made concept, can accurately explain real world phenomena
astounds me. This deep fascination in our own world that we exist in motivated me to further 
explore that concept in another context - through programming.  Not only would working on this
project be something truly exciting for me, it would also be greatly beneficial
to the future of the field. Targeting a younger demographic is a 
key component of this project, with the aim of sparking interest
in this field by demonstrating how fun physical phenomena may be.
Additionally, the prospect of accurately and precisely simulating 
real-world actions would also quite challenging to me.   

<!-- ####Instructions for Grader
 - Step 1: create a new ball pit, or load the saved ball pit when opening the application
 - Step 2: to add ball to ball pit, click on settings icon, and add ball (or press "A")
 - Step 3: to trigger audiovisual component, use the arrow keys to make balls collide with each other
 - Step 4: to save ball pit, click save ball pit in settings.  -->


#### Phase 1: Stories
As a user, I want to...
- [x] see the balls _move_
- [x] _add_ balls to the BallPit
- [x] _delete_ balls from the BallPit
- [x] see the balls _interact_ with each other and the environment

#### Phase 2: Stories
As a user, I want to...
- [x] _save_ the current state of the sandbox
- [x] _load_ another sandbox's state
- [x] _prompt_ the user to save when exiting sandbox


#### Phase 4: Task 2
I chose to design a class such that it is robust. Particularly, I decided to use this on the Ball class, such that balls with impossible mass and radius cannot be constructed. The methods which use this robust design are the constructors which create each ball.

#### Phase 4: Task 3
- Problem 1: Poor cohesion regarding the main class. In this particular class, one of the main issues is that in addition to running the main program (specifically ball pit related functions), the main class had also included methods responsible for mouse events and key events. To solve this lack of cohesion, I made a separate class to handle mouse events and key events called MainEventHandler.
- Problem 2: A similar problem existed for the PitPanel class, which should be responsible only for rendering the ball pit onto the JFrame. However, there was poor cohesion, as PitPanel was also responsible for handling user input, in the form of key events and mouse events. To fix this poor cohesion, I made another class called PitPanelEventHandler in order to handle key and mouse events from the user while the ball pit is being rendered.