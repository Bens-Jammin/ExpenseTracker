import matplotlib.pyplot as plt
import numpy as np


"""
param: expenses --> dictionary (string -> float)
param: income   --> dictionary (string -> float)

creates a bar graph showing the users total in and out spending based on categories
"""
def create_graph(expenses: dict, income: dict):
    # Sample data
    categories = ['Category A', 'Category B', 'Category C']
    positive_values = [10, 5, 12]  # Positive values
    negative_values = [-8, -3, -6]  # Negative values

    # Calculate the range for the x-axis
    x_range = max(max(positive_values), abs(min(negative_values)))

    # Plot the bars
    fig, ax = plt.subplots()
    ax.bar(categories, positive_values, color='green')
    ax.bar(categories, negative_values, color='red')

    # Customize the appearance
    ax.spines['bottom'].set_position('zero')  # Move the bottom spine to zero position
    ax.spines['top'].set_color('none')  # Remove the top spine
    ax.spines['right'].set_color('none')  # Remove the right spine
    ax.yaxis.set_ticks_position('left')  # Set the y-ticks position to left
    ax.xaxis.set_ticks_position('bottom')  # Set the x-ticks position to bottom
    ax.yaxis.set_tick_params(width=0.5)  # Set the width of y-ticks
    ax.xaxis.set_tick_params(width=0)  # Hide the x-ticks

    # Add labels to the bars
    for i in range(len(categories)):
        y = max(positive_values[i], negative_values[i]) + 0.5  # Adjust the label position
        color = 'green' if positive_values[i] > negative_values[i] else 'red'
        ax.text(i, y, f'{positive_values[i]:.1f}', ha='center', va='bottom', color=color)
        ax.text(i, -y, f'{negative_values[i]:.1f}', ha='center', va='top', color=color)

    # Set the limits for the y-axis
    ax.set_ylim(-x_range, x_range)

    # Add a title and show the plot
    plt.title('Diverging Bar Chart')
    plt.show()
