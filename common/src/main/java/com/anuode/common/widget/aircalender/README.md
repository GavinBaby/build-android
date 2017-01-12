日期控件

1. 加载此控件

         <TextView
             android:id="@+id/calendarCenter"
             style="@style/main_bar_text_style"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_centerHorizontal="true"
             android:layout_margin="8dp" />

         <ImageButton
             android:id="@+id/calendarLeft"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_alignParentLeft="true"
             android:background="@null"
             android:contentDescription="@null"
             android:padding="8dp"
             android:src="@drawable/calendar_month_left" />

         <ImageButton
             android:id="@+id/calendarRight"
             android:layout_width="wrap_content"
             android:layout_height="wrap_content"
             android:layout_alignParentRight="true"
             android:background="@null"
             android:contentDescription="@null"
             android:padding="8dp"
             android:src="@drawable/calendar_month_right" />

         <com.anuode.common.widget.aircalender.CalendarView
             android:id="@+id/calendar"
             android:layout_width="fill_parent"
             android:layout_height="match_parent"
             android:layout_alignParentLeft="true"
             android:layout_below="@+id/calendarCenter">

         </com.anuode.common.widget.aircalender.CalendarView>

2. 初始化控件

             calendar = (CalendarView) findViewById(R.id.calendar);
             calendar.setSelectMore(false); //单选

             calendarLeft = (ImageButton) findViewById(R.id.calendarLeft);//上一月
             calendarCenter = (TextView) findViewById(R.id.calendarCenter);//中间显示的日期
             calendarRight = (ImageButton) findViewById(R.id.calendarRight);//下一月

3. 获得当前时间

             format = new SimpleDateFormat("yyyy-MM-dd");

4. 设置日历时间

         try {
                     //设置日历日期
              Date date = format.parse("2015-01-01");
              calendar.setCalendarData(date);
              }catch (ParseException e){
                    e.printStackTrace();
                    }

5. 给上一月，下一月设置监听

          calendarLeft.setOnClickListener(new View.OnClickListener() {

                     @Override
                     public void onClick(View v) {
                         //点击上一月 同样返回年月
                         String leftYearAndmonth = calendar.clickLeftMonth();
                         String[] ya = leftYearAndmonth.split("-");
                         calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
                     }
                 });

          calendarRight.setOnClickListener(new View.OnClickListener() {

                     @Override
                     public void onClick(View v) {
                         //点击下一月
                         String rightYearAndmonth = calendar.clickRightMonth();
                         String[] ya = rightYearAndmonth.split("-");
                         calendarCenter.setText(ya[0] + "年" + ya[1] + "月");
                     }
                 });

6. 设置控件监听，可以监听到点击的每一天（大家也可以在控件中根据需求设定）

         calendar.setOnItemClickListener(new CalendarView.OnItemClickListener() {

              @Override
                     public void OnItemClick(Date selectedStartDate,
                                             Date selectedEndDate, Date downDate) {
                         if (calendar.isSelectMore()) {
                             Toast.makeText(getApplicationContext(), format.format(selectedStartDate) + "到" + format.format(selectedEndDate), Toast.LENGTH_SHORT).show();
                         } else {
                             Toast.makeText(getApplicationContext(), format.format(downDate), Toast.LENGTH_SHORT).show();
                         }
                     }
                 });