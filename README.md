## Project Description

**A project to analyse AI generated code. Part of the [ChatGPT guided project](https://www.archi-lab.io/projects/ss23/gp_chatgpt_ss23.html) under the [Digital Sciences](https://digital-sciences.de/) Master's degree at [TH Köln](https://www.th-koeln.de/).**

AI chatbots (ChatGPT, Bard, etc) were used to generate code solutions to Java programming tasks (milestone assignments) taken from the 2021 presentation of the [Software Engineering 2](https://www.archi-lab.io/regularModules/ss22/st2_ss22.html) Bachelor's course at TH Köln.

Milestone 0 involves writing code to navigate a robot around a room with obstacles. The AI solutions to this task are compared to the student-written solutions.

## Project Metadata

#### Notebooks code:

| **Notebook name** | **Contents** |
|---|---|
| [Clone Student Repos](https://github.com/Natasha-R/ChatGPT-Project/blob/main/1.%20Clone%20Student%20Repos.ipynb) | The student solutions are cloned from GitLab and stored locally. <br>This notebook is not re-usable for data privacy reasons,<br>but the anonymised student solutions are available in the folder "raw_solutions".  |
| [Scenarios](https://github.com/Natasha-R/ChatGPT-Project/blob/main/2.%20Scenarios.ipynb) | Each student was randomly assigned a different scenario accompanying the task.<br>The variants of these are explored, to ensure that the AI generated solutions include the same variation. |
| [Process All Exercise Solutions](https://github.com/Natasha-R/ChatGPT-Project/blob/main/3.%20Process%20All%20Exercise%20Solutions.ipynb) | All of the solutions to all of the milestones from all sources are processed and stored as .json files.<br>The resulting datasets can be found in the folder "processed_solutions". |
| [Process Milestone 0 Solutions](https://github.com/Natasha-R/ChatGPT-Project/blob/main/4.%20Process%20Milestone%200%20Solutions.ipynb) | The solutions from all sources to the milestone 0 tasks are processed separately in preparation for closer analysis.<br>The resulting datasets are stored in both .json and .csv format in the folder "processed_solutions". |

#### Directory structure:

| **Folder name** | **Contents** |
|---|---|
| clean_milestones | The empty task repos, used as the starting point to generate the task solutions. |
| raw_solutions | The full task repos including the code written to solve the task.<br>Divided into subfolders indicating the source of the solution (ChatGPT, student, etc). |
| processed_solutions | The task solution repos are processed and stored as analysable datasets,<br>stored in both .json and .csv formats. |
| milestone_0_prompts | The prompts used for the chatbots to generate the solutions. |

#### Datasets schema:

| **Attribute** | **Description** | **Example Contents** (Strings) |
|---|---|---|
| source | The source of the generated solution - student or named AI. | "student", "gpt3.5", "bing", "bard" |
| milestone | The milestone task the solution relates to | "m0" |
| name | For the student solutions, this is the anonymised name.<br>For the AI solutions, this is a unique identifier. | "student102"<br>"4" |
| style | Only used for the AI generated solutions.<br>The prompted style in which the solution was generated.<br>May have no style, "clean code" or be "messy/inconsistently styled".  | "plain", "cc", "styled" |
| version | Only used for the Bing chat generated solutions.<br>Refers to the version of Bing chat used to generate the solution. | "creative", "balanced", "concise" |
| file_name | Only relevant for the non-concatenated dataset.<br>The name of the file the code solution was stored in. | "Exercise0.java" |
| code | The code solution for the task. | "public class Exercise0..." |