[toolbar_resource]: https://contextneutral.com/story/android-toolbar-101-everything-you-should-know
[let_the_content_width_match_parent]: https://stackoverflow.com/questions/29951764/why-doesnt-my-custom-action-bar-view-match-parent-when-using-appcompat-and-to
[let_the_content_width_match_parent2]:https://stackoverflow.com/questions/12883732/how-to-display-custom-view-in-actionbar/16652485#16652485

Some toolbar resources
===
[here][toolbar_resource]

to let the content width match_parent:
---
-  you can use relative layout as the parent and it will do the work

or

- get the actionbar and set the custom view manually with layout params to match parent

```
supportActionBar?.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
val params = ActionBar.LayoutParams(
    ActionBar.LayoutParams.MATCH_PARENT,
    ActionBar.LayoutParams.MATCH_PARENT
)
supportActionBar?.setCustomView(customToolbarContent2, params)
```
