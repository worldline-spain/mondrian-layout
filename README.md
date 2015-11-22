#MondrianLayout

![alt text](https://raw.githubusercontent.com/worldline-spain/mondrian-layout/master/art/mondrian1.png "Portrait") ![alt text](https://raw.githubusercontent.com/worldline-spain/mondrian-layout/master/art/mondrian2.png "Landscape")

MondrianLayout allows to create Android layouts with the containing elements disposed in a pure grid organization. You just define the size of your grid and the cells occupied by each element inside.

You can:

* Dispose elements in the layout indicating the starting coordinates in the grid and the width and height in cells
* Overlap elements, the ones declared later in the layout will be on top of the ones declared before
* Nest MondrianLayouts, so you can have a grid that contains an element with its own Grid

## Getting MondrianLayout

Grab the mondrianlayout module and add it to your application.

We are working on publishing this component into jcentral for your own convencience. Stay tuned!

## Usage

First, add a namespace into your layout for the non-system resources, for example:

```xml
    xmlns:mondrian="http://schemas.android.com/apk/res-auto"
```

Now you can include a MondrianLayout into your layout:

```xml
<com.worldline.mondrian.MondrianLayout
        android:id="@+id/mondrian"
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:background="#000"
        mondrian:borderRatio="12"
        mondrian:columns="6"
        mondrian:rows="8">
```

The specific attributes are:

* columns: number of columns of the grid
* rows: number of rows of the grid
* borderRatio: size of the border between cells. The resulting width of the cell will be divided by this number to get the size of the border

Now you can put elements inside your MondrianLayout:

```xml
<TextView xmlns:android="http://schemas.android.com/apk/res/android"
            android:layout_width="0dp"
            android:layout_height="0dp"
            android:background="#303F9F"
            android:gravity="center"
            android:text="1"
            android:textColor="@android:color/white"
            android:textSize="20sp"
            mondrian:layout_column="0"
            mondrian:layout_height="1"
            mondrian:layout_row="0"
            mondrian:layout_width="4" />
```

Here the attributes:

* android:layout_width/android:layout_height: these two attributes are not used, but Android forces to include them in the definition of the View. Just set any value, it will be ignored.
* layout_column/layout_row: starting column/row where to put this view. Columns and rows start in zero until size-1
* layout_width/layout_height: number of cells this view will occupy in width/heigh

You can also nest MondrianLayout elements one inside of the another, so the child MondrianLayout could have a different column/row configuration than its ancestor.

The sample app has a layout with several examples, including nested ones.

## TODO

There are still some things to improve, including:

* Ability to change grid properties during runtime
* Ability to animate cell size/position 
* Do something with the android:layout_width/android:layout_height property, as having to include it not to be used is not an elegant solution

Anything else, feel free to post an Issue or perform a Pull Request

## LICENSE ##

    Copyright 2015 Wordline Spain

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


