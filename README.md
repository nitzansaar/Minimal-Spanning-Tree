# Minimal Spanning Tree (MST) Algorithms

This project implements two algorithms, Kruskal's algorithm and Prim's algorithm, to compute the Minimum Spanning Tree (MST) of a given graph. The MST is a subset of the edges in a graph that connects all the vertices while minimizing the total edge cost. The project provides classes to represent graphs, edges, and various MST algorithms.

## Contents

- [Project Structure](#project-structure)
- [Usage](#usage)
- [What I Learned](#what-i-learned)

## Project Structure

The project is organized into several Java classes:

- `Edge`: Represents an edge between two vertices in a graph. Implements the `Comparable` interface to compare edges based on their cost.
- `Graph`: Represents a graph composed of city nodes and edges. Reads graph information from a file and constructs the graph.
- `MSTAlgorithm`: Parent class for MST algorithms, containing common methods and fields. Subclasses include `KruskalAlgorithm` and `PrimAlgorithm`.
- `KruskalAlgorithm`: Subclass of `MSTAlgorithm`, implements Kruskal's algorithm to compute the MST of the graph.
- `PrimAlgorithm`: Subclass of `MSTAlgorithm`, implements Prim's algorithm to compute the MST of the graph.
- `MinHeap`: Represents a priority queue implemented as a min-heap, used in Prim's algorithm.
- `DisjointSets`: Represents the Disjoint Sets data structure, used in Kruskal's algorithm.
- `MSTDriver`: The main driver class that loads a graph from a file and initiates the GUI for interaction.
- `GUIApp`: A class that deals with the graphical user interface of the project. Creates a window with a panel that displays the map of the US and allows the user to click on Kruskal's or Prim's button to run the corresponding algorithm and display the MST edges.

## Usage

To use the project, follow these steps:

1. Compile the Java source files using your preferred Java compiler.
2. Run the `MSTDriver` class and provide the path to an input file as a command-line argument. This file should contain graph information in a specified format.
3. The GUI will display the graph and provide options to visualize the MST computed by Kruskal's or Prim's algorithm.

## What I Learned

Through this project, I gained experience in several key areas:

- **Graph Representation**: Learned how to represent graphs using adjacency lists, edges, and nodes.
- **Minimum Spanning Tree Algorithms**: Implemented both Kruskal's and Prim's algorithms to find the MST of a graph.
- **Priority Queue Implementation**: Implemented a priority queue using a min-heap, which is essential for Prim's algorithm.
- **Disjoint Sets Data Structure**: Implemented a Disjoint Sets data structure for efficient cycle detection in Kruskal's algorithm.
- **Graph Visualization**: Interacted with GUI components to visualize graphs and their MSTs.
- **Java Programming**: Improved Java programming skills, including class design, inheritance, and interfaces.

Overall, this project provided hands-on experience in working with graph algorithms and data structures, contributing to a deeper understanding of algorithmic problem-solving and software development.
