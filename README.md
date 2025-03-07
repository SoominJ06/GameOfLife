# Game Of Life 🧬
This project is a variation of Conway's Game of Life, a well-known cellular automaton. The original model simulates the evolution of cells on a grid based on simple rules, resulting in complex, emergent patterns. In this version, the simulation incorporates Plants, Herbivores, Carnivores, and Omnivores, each with unique behaviours and rules for interaction as they navigate the environment.
<h6>* This project was created in March 2024. </h6>

## Lifeforms
- **Plants 🌱 (Green)**:
  - Behavior: Plants stay still and do not move, acting as a food source for herbivores, carnivores, and omnivores.
  - Reproduction: Plants can reproduce if they have at least 2 neighbouring plants, at least 3 empty surrounding cells, and no adjacent food sources.

- **Herbivores 🐰 (Yellow)**:
  - Behavior: Herbivores eat feed on plants, and must consume food within 5 moves to survive. They can move around the cells to find food and reproduce.
  - Reproduction: Herbivores can reproduce if they have at least 1 neighbouring herbivore, at least empty surrounding cells, and at least 2 adjacent cells containing its food source (plants).

- **Carnivores 🦊 (Red)**:
  - Behavior: Carnivores eat herbivores and omnivores. They must consume food within 5 moves to survive. They can move around the cells to find food and reproduce.
  - Reproduction: Carnivores can reproduce if they have at least 1 other neighbouring carnivore, at least three empty surrounding cells, and at least 2 adjacent cells containing its food source (herbivores or omnivores).

- **Omnivores 👨 (Blue)**:
  - Color: Blue
  - Behavior: Omnivores can eat plants, herbivores, and carnivores. They must consume food within 5 moves to survive. They can move around the cells to find food and reproduce.
  - Reproduction: Omnivores can reproduce if they have at least 1 other neighbouring omnivore, at least three empty surrounidng cells, and at least 1 adjacent cell containing its food source (plants, herbivores, or carnivores).


## Demo Video
https://github.com/user-attachments/assets/27be5f2b-325a-4e19-83e5-dad2732b6869


## Usage
To run the simulation:
1. Clone this repository to your local machine.
2. Open the project in your preferred IDE.
3. Compile and run the main class.


## Initial Design:
Made by:
 - Soomin Jeong [GitHub](https://github.com/SoominJ06)
 - Danton Soares [GitHub](https://github.com/Danton1)

![UML Initial Design](https://github.com/Danton1/Game-of-Life/assets/107024401/6dc2685c-c8d3-43ea-9120-db79ab019e30)

## Initial prompt:

You are going to create a simulation of a simple world. Often referred to as “The game of Life” (not related to the Parker Brothers board game). The world starts off with Plants and Herbivores (plant eaters) on a grid randomly placed. The grid displays the plants (green) and Herbivores (yellow) by filling in the squares where they are found. Blank squares represent empty areas. Herbivores “graze” by moving around the grid eating plants they find. Herbivores must find a plant to eat before 5 “turns” have passed or they die. A “turn” is a step in time which occurs when a user clicks anywhere on the window displaying the world. Herbivores move by checking neighboring cells and randomly picking one. They cannot move to a neighboring cell that contains a Herbivore. They move 1 cell per turn. Plants do not move. Plant however will seed. Each plant will send seeds to a random neighboring empty cell assuming that there are at least 3 empty cells to send seeds to and there are exactly 4 other plants to help cross pollenate. 

It is important that you think about future changes before building your simulation. Movement patterns may change, procreation by animals, new rules governing behaviors, and physical representations. This does not imply that all of these changes will occur, nor does it preclude others from being introduced. By carefully thinking about what could be added or changed and designing with that flexibility in mind you’ll find the future parts for assignment 2 easier to accommodate.
