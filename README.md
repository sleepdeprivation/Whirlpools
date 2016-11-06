# Whirlpools

A weird little thing I made in scala - my second scala project

Looks like this one is also full of deprecation errors! Oh well


Generates evolving cellular-automata-like animations. Here is a single "frame"

        ⇩ ⇩ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ 
        ⇩ ⇩ ⇩ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇦ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . ⇦ ⇦ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . . ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . . ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . . ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . . ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . . ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇩ . . . . . . ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇩ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇧ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇩ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇧ ⇧ ⇧ ⇧ 
        ⇩ ⇩ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇧ ⇧ ⇧ 
        ⇩ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇧ ⇧ 
        ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇨ ⇧ 

Currently running the main function will show you a little animation that best describes what it does
This uses my previous repo as an engine:

https://github.com/sleepdeprivation/ProbabilityArray.git

