/* ==================================================================
   GLOBAL & ROOT STYLES (White Theme)
   ================================================================== */
.root {
    -fx-font-family: 'Segoe UI', 'Roboto', sans-serif;
    -fx-background-color: #F0F2F5; /* Light grey background for the app */
    -fx-font-size: 14px;
    -fx-text-fill: #333333; /* Darker text for readability on light background */
}

/* ==================================================================
   NAVIGATION BAR (LEFT PANEL)
   ================================================================== */
.nav-bar {
    -fx-background-color: #FFFFFF; /* White background for nav bar */
    -fx-border-color: #E0E0E0;
    -fx-border-width: 0 1px 0 0; /* Subtle right border */
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.05), 5, 0.0, 1, 0); /* Soft shadow */
}

.app-title {
    -fx-font-size: 28px;
    -fx-font-weight: bold;
    -fx-fill: #0078D7; /* Primary blue accent */
    -fx-effect: dropshadow(one-pass-box, rgba(0,0,0,0.1), 2, 0, 1, 1);
}

.nav-button {
    -fx-background-color: transparent;
    -fx-text-fill: #555555; /* Dark grey text */
    -fx-font-size: 15px;
    -fx-pref-width: 180px;
    -fx-padding: 10px 15px;
    -fx-background-radius: 8px;
    -fx-graphic-text-gap: 15px;
    -fx-transition: -fx-background-color 0.2s ease, -fx-text-fill 0.2s ease;
}

.nav-button:hover {
    -fx-background-color: #E6F2FF; /* Light blue on hover */
    -fx-text-fill: #0078D7; /* Primary blue text on hover */
}

.nav-button:focused {
     -fx-background-color: #D2E7FF; /* Slightly darker blue for focused */
     -fx-text-fill: #005BB5;
}

/* ==================================================================
   MAIN VIEW PANES (CENTER CONTENT)
   ================================================================== */
.view-pane {
    -fx-background-color: #F0F2F5; /* Consistent light background */
}

.view-title {
    -fx-font-size: 26px;
    -fx-font-weight: 600;
    -fx-text-fill: #333333;
    -fx-padding-bottom: 10px; /* Space below title */
}

/* ==================================================================
   DASHBOARD SPECIFIC STYLES
   ================================================================== */
.dashboard-pane {
    -fx-background-color: #F0F2F5;
}

.dashboard-title {
    -fx-font-size: 32px;
    -fx-font-weight: bold;
    -fx-text-fill: #333333;
}

.dashboard-card {
    -fx-background-color: #FFFFFF; /* White card background */
    -fx-background-radius: 12px;
    -fx-padding: 30px;
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 10, 0.0, 0, 4); /* Softer shadow for light theme */
    -fx-min-width: 250px;
    -fx-alignment: center;
    -fx-border-color: #E0E0E0;
    -fx-border-width: 1px;
    -fx-border-radius: 12px;
}

.card-header {
    -fx-font-size: 18px;
    -fx-text-fill: #666666; /* Medium grey for headers */
}

.card-value {
    -fx-font-size: 48px;
    -fx-font-weight: bold;
    -fx-text-fill: #0078D7; /* Primary blue accent */
}


/* ==================================================================
   TABLE VIEW STYLES
   ================================================================== */
.table-view {
    -fx-background-color: #FFFFFF;
    -fx-background-radius: 8px;
    -fx-border-color: #E0E0E0;
    -fx-border-width: 1px;
    -fx-border-radius: 8px;
}

.table-view .column-header-background {
    -fx-background-color: #F8F8F8; /* Light grey header background */
    -fx-border-color: #E0E0E0;
    -fx-border-width: 0 0 1px 0; /* Bottom border for header */
}

.table-view .column-header .label {
    -fx-text-fill: #333333;
    -fx-font-weight: bold;
    -fx-font-size: 14px;
}

.table-view .table-cell {
    -fx-text-fill: #444444;
    -fx-border-color: #E0E0E0;
    -fx-border-width: 0 0 1px 0;
    -fx-padding: 8px 10px;
}

.table-row-cell:selected {
    -fx-background-color: #D2E7FF; /* Light blue for selected row */
    -fx-text-fill: #000000;
}

/* Style for CheckBox in table */
.table-cell .check-box .box {
    -fx-background-color: #FFFFFF;
    -fx-border-color: #999999;
    -fx-border-radius: 3px;
    -fx-padding: 2px;
}
.table-cell .check-box:selected .box {
    -fx-background-color: #0078D7;
    -fx-border-color: #0078D7;
}
.table-cell .check-box:selected .box .mark {
    -fx-background-color: white;
}


/* ==================================================================
   GENERAL CONTROL STYLES (BUTTONS, TEXTFIELDS, COMBOBOX, DATEPICKER)
   ================================================================== */
.button {
    -fx-background-color: #0078D7; /* Primary blue for main buttons */
    -fx-text-fill: white;
    -fx-font-weight: bold;
    -fx-background-radius: 8px;
    -fx-padding: 10px 20px;
    -fx-cursor: hand;
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.1), 5, 0.0, 0, 2);
    -fx-transition: -fx-background-color 0.2s ease, -fx-effect 0.2s ease;
}
.button:hover {
    -fx-background-color: #005BB5; /* Darker blue on hover */
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.15), 8, 0.0, 0, 3);
}
.button:pressed {
    -fx-background-color: #004080;
    -fx-effect: innershadow(gaussian, rgba(0, 0, 0, 0.1), 5, 0, 0, 1);
}

.action-button-green {
    -fx-background-color: #28a745; /* Green for success actions */
    -fx-text-fill: white;
}
.action-button-green:hover {
    -fx-background-color: #218838;
}

.text-field, .combo-box, .date-picker {
    -fx-background-color: #FFFFFF;
    -fx-text-fill: #333333;
    -fx-prompt-text-fill: #999999;
    -fx-border-color: #E0E0E0;
    -fx-border-width: 1px;
    -fx-border-radius: 8px;
    -fx-padding: 8px 10px;
    -fx-font-size: 14px;
    -fx-transition: -fx-border-color 0.2s ease, -fx-effect 0.2s ease;
}
.text-field:focused, .combo-box:focused, .date-picker:focused {
    -fx-border-color: #0078D7;
    -fx-effect: dropshadow(gaussian, rgba(0, 120, 215, 0.2), 8, 0, 0, 0); /* Subtle blue glow */
}

/* Combo Box specific tweaks */
.combo-box .list-cell {
    -fx-text-fill: #333333;
    -fx-background-color: white;
}
.combo-box .list-cell:hover {
    -fx-background-color: #E6F2FF;
}

/* DatePicker arrow button */
.date-picker .arrow-button {
    -fx-background-color: #0078D7;
    -fx-background-radius: 8px;
    -fx-padding: 5px;
}
.date-picker .arrow-button .arrow {
    -fx-background-color: white;
}
.date-picker .arrow-button:hover {
    -fx-background-color: #005BB5;
}

/* DialogPane styling */
.dialog-pane {
    -fx-background-color: #FFFFFF;
    -fx-background-radius: 12px;
    -fx-effect: dropshadow(gaussian, rgba(0,0,0,0.15), 15, 0.0, 0, 5);
    -fx-padding: 20px;
}
.dialog-pane .header-panel {
    -fx-background-color: #F8F8F8;
    -fx-padding: 15px;
    -fx-border-radius: 12px 12px 0 0;
}
.dialog-pane .header-panel .label {
    -fx-font-size: 18px;
    -fx-font-weight: bold;
    -fx-text-fill: #333333;
}
.dialog-pane .content {
    -fx-padding: 20px 0;
    -fx-text-fill: #444444;
}
.dialog-pane .button-bar {
    -fx-padding: 15px 0 0 0;
    -fx-background-color: transparent;
}
.dialog-pane .button {
    -fx-min-width: 80px;
}
.dialog-pane .button:default {
    -fx-background-color: #0078D7;
    -fx-text-fill: white;
}
.dialog-pane .button:default:hover {
    -fx-background-color: #005BB5;
}
.dialog-pane .button:cancel {
    -fx-background-color: #CCCCCC;
    -fx-text-fill: #333333;
}
.dialog-pane .button:cancel:hover {
    -fx-background-color: #BBBBBB;
}

/* Add these styles to the end of your styles.css file */

/* Styles for the right-side form pane in the billing view */
.form-pane {
    -fx-background-color: #FFFFFF;
    -fx-background-radius: 12px;
    -fx-border-color: #E0E0E0;
    -fx-border-width: 1px;
    -fx-border-radius: 12px;
    -fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.08), 10, 0.0, 0, 3);
}

.form-title {
    -fx-font-size: 20px;
    -fx-font-weight: 600;
    -fx-text-fill: #333333;
}

.total-label {
    -fx-font-size: 18px;
    -fx-font-weight: bold;
    -fx-text-fill: #0078D7;
    -fx-padding: 10px 0;
}

.filter-bar {
    -fx-alignment: CENTER_LEFT;
    -fx-padding: 0 0 10px 0; /* Add some space below the buttons */
}


/* Add this new style to the end of your styles.css file */

.action-button-red {
    -fx-background-color: #dc3545; /* A standard red color for danger/cancel actions */
    -fx-text-fill: white;
}
.action-button-red:hover {
    -fx-background-color: #c82333;
}

/* Add this to your styles.css file if it's not already there */

.filter-bar {
    -fx-alignment: CENTER_LEFT;
    -fx-padding: 0 0 10px 0; /* Add some space below the buttons */
}