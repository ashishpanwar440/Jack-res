package mega.math.machine;
import java.util.ArrayList;
import java.awt.Color;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.awt.Graphics;
import java.awt.Image;
import javax.swing.SwingUtilities;
import java.io.File;
import java.util.Scanner;
import javax.swing.DefaultListModel;
import javax.swing.JTextField;
import java.io.BufferedWriter;
import java.io.FileWriter;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;

public class MMM extends javax.swing.JFrame {
    //Fo da sciuntific convertion
    ArrayList<UnitList> unitCat = new ArrayList();
    // <editor-fold defaultstate="collapsed" desc="Defaut unit string">
    String DEFAUL_UNITS = "<Length>\n" +
            "in (Inch), 0.0254, 0\n" +
            "ft (Foot), 0.0254*12, 0\n" +
            "yd (Yard), 0.0254*12*3, 0\n" +
            "mi (Mile), 1609.344, 0\n" +
            "pm (Picometer), 10^-12, 0\n" +
            "nm (Nanometer), 10^-9, 0\n" +
            "µm (Micrometer), 10^-6, 0\n" +
            "mm (Millimeter), 10^-3, 0\n" +
            "cm (Centimeter), 10^-2, 0\n" +
            "dm (Decameter), 10^-1, 0\n" +
            "m (Meter), 1, 0\n" +
            "dam (Decimeter), 10^1, 0\n" +
            "hm (Hectometer), 10^2, 0\n" +
            "km (Kilometer), 10^3, 0\n" +
            "Mm (Megameter), 10^6, 0\n" +
            "Gm (Gigameter), 10^9, 0\n" +
            "Tm (Terameter), 10^12, 0\n" +
            "Pm (Petameter), 10^15, 0\n" +
            "Em (Exameter), 10^18, 0\n" +
            "Zm (Zettameter), 10^21, 0\n" +
            "Å (Ångström), 10^-10, 0\n" +
            "League, 1609.344*3, 0\n" +
            "Fathom, 0.0254*12*6, 0\n" +
            "NM (Nautical Mile), 1852, 0\n" +
            "Earth Radius, 6371000, 0\n" +
            "AU (Astronomical Unit), 149597870700, 0\n" +
            "ly (Light Year), 9460730472580800, 0\n" +
            "pc (Parsec), 30856775814671900, 0\n" +
            "</Length>\n" +
            "<Mass>\n" +
            "mg (Milligram), 10^-6, 0\n" +
            "g (Gram), 10^-3, 0\n" +
            "kg (Kilogram), 1, 0\n" +
            "t (Metric Ton), 10^3, 0\n" +
            "lb (Pound), 0.453592, 0\n" +
            "Long ton, 1016.05, 0\n" +
            "Short Ton, 907.185, 0\n" +
            "Ounce, 1/35.274, 0\n" +
            "Stone, 6.35029, 0\n" +
            "sl (Slug), 14.6, 0\n" +
            "</Mass>\n" +
            "<Temperature>\n" +
            "°C (Celsius), 1, 0\n" +
            "°F (Fahrenheit), 5/9, 32\n" +
            "K (Kelvin), 1, 273.15\n" +
            "°R (Rankine), 5/9, 491.67\n" +
            "°De (Delisle), -2/3, 150\n" +
            "°N (Newton), 100/33, 0\n" +
            "°Ré (Réaumur), 5/4, 0\n" +
            "°Rø (Rømer), 40/21, 7.5\n" +
            "</Temperature>\n" +
            "<Area>\n" +
            "km^2 (Square Kilometer), 1, 0\n" +
            "ha (Hectare), 10^-1, 0\n" +
            "m^2 (Square Meter), 10^-6, 0\n" +
            "mi^2 (Square Mile), 2.58999, 0\n" +
            "ac (Acre), 1/247.105, 0\n" +
            "yd^2 (Square Yard), 8.3613*10^-7, 0\n" +
            "ft^2 (Square Foot), 9.2903*10^-8, 0\n" +
            "in^2 (Square Yard), 6.4516*10^-10, 0\n" +
            "</Area>\n" +
            "<Volume>\n" +
            "m^3 (Cubic Meter), 1, 0\n" +
            "L (Liter), 10^-3, 0\n" +
            "mL (Milliliter), 10^-6, 0\n" +
            "US gal, 1/264.172, 0\n" +
            "US quart, 1/1056.69, 0\n" +
            "US pint, 1/2113.38, 0\n" +
            "US cup, 1/4226.75, 0\n" +
            "US oz, 1/33814, 0\n" +
            "US tbsp, 1/67628, 0\n" +
            "US tsp, 1/202884, 0\n" +
            "Imperial gal, 1/219.969, 0\n" +
            "Imperial quart, 1/879.877, 0\n" +
            "Imperial pint, 1/1759.75, 0\n" +
            "Imperial oz, 1/35195.1, 0\n" +
            "Imperial tbsp, 1/56312.1, 0\n" +
            "Imperial tsp, 1/168936, 0\n" +
            "ft^3 (Cubic Foot), 1/35.3147, 0\n" +
            "in^3 (Cubic Inch), 1/61023.7, 0\n" +
            "</Volume>\n" +
            "<Time>\n" +
            "ns (Nanosecond), 10^-9, 0\n" +
            "µs (Microsecond), 10^-6, 0\n" +
            "ms (Millisecond), 10^-3, 0\n" +
            "s (Second), 1, 0\n" +
            "min (Minute), 60, 0\n" +
            "hr (Hour), 3600, 0\n" +
            "Day, 86400, 0\n" +
            "Week, 604800, 0\n" +
            "Month, 4.34812*604800, 0\n" +
            "Year, 12*4.34812*604800, 0\n" +
            "Decade, 10*12*4.34812*604800, 0\n" +
            "Century, 100*12*4.34812*604800, 0\n" +
            "Millenium, 1000*12*4.34812*604800, 0\n" +
            "</Time>\n" +
            "<Digital Storage>\n" +
            "bit, 1, 0\n" +
            "B (Byte), 2^10, 0\n" +
            "KB (Kilobyte), 2^10, 0\n" +
            "MB (Megabyte), 2^20, 0\n" +
            "GB (Gigabyte), 2^30, 0\n" +
            "TB (Terabyte), 2^40, 0\n" +
            "PB (Petabyte), 2^50, 0\n" +
            "</Digital Storage>\n";// </editor-fold>
    
    //Fo da graph:
    double scaleX = 1;
    double scaleY = 1;
    double offX = 0;
    double offY = 0;
    double ZOOM_MULTIPLIER = 1.5;
    int DIVISION_LENGTH = 20;
    Image offScrImg;
    Graphics offScrGrp;
    Color BOLD_LINES = new Color(0, 0, 0);
    Color BACK_COLOR = new Color(255, 255, 255);
    Color THIN_LINES = new Color(180, 180, 255);
    Color GRAPH_LINES = new Color(255, 0, 0);
    double originalX = 0, originalY = 0;
    double pressedX = 0, pressedY = 0;
    boolean ctrlDown = false;
    boolean shiftDown = false;
    double limitLo, limitHi;
    
    public MMM() {
        initComponents();
        resultBox.setLineWrap(true);
        loadUnit();
    }
    
    private void loadUnit() {
        String unitFromFile = "";
        try {
            File unitFile = new File("Unit.uni");
            Scanner scan = new Scanner(unitFile);
            while (scan.hasNext()) {
                unitFromFile += scan.nextLine() + "\n";
            }
            scan.close();
        } catch (Exception e) {
            unitFromFile = DEFAUL_UNITS;
        }
        try {
            loadUnitFromString(unitFromFile);
        } catch (Exception e) {
            System.out.println(e.toString());
            unitCat = new ArrayList();
            try {
                loadUnitFromString(DEFAUL_UNITS);
            } catch (Exception ex) {}
        }
    }
    private void loadUnitFromString(String unitFromFile) throws Exception {
        String className;
        String content;
        String[] tokens;
        int beginIndex;
        int endIndex;
        while (!unitFromFile.isEmpty() && unitFromFile.charAt(0) == '<') {
            //Get class name
            className = unitFromFile.substring(1,
                    unitFromFile.indexOf("\n") - 1);
            unitCat.add(new UnitList(className));
            
            //Get content of class
            beginIndex = className.length() + 3;
            endIndex = unitFromFile.indexOf("</" + className + ">") - 1;
            content = "";
            if (endIndex < 0) {
                throw new Exception();
            }
            if (endIndex - beginIndex > 0) {
                content = unitFromFile.substring(className.length() + 3,
                        unitFromFile.indexOf("</" + className + ">") - 1).trim();
            }
            //Slice each line
            if (!content.isEmpty()) {
                String[] lines = content.split("\n");
                for (int i = 0; i < lines.length; i++) {
                    tokens = lines[i].split(",\\s*");
                    unitCat.get(unitCat.size() - 1).unitList.add(new Unit(
                            tokens[0], tokens[1], tokens[2]));
                    content = content.substring(content.indexOf("\n") + 1);
                }
            }
            
            unitFromFile = unitFromFile.substring(
                    unitFromFile.indexOf("</" + className + ">")
                            + ("</" + className + ">").length() + 1);
        }
        loadComboBox();
        loadComboBox(unitComboBox1);
        loadComboBox(unitComboBox2);
        loadListBox();
    }
    
    private void loadComboBox() {
        convertionType.removeAllItems();
        for (int i = 0; i < unitCat.size(); i++) {
            convertionType.addItem(unitCat.get(i).name);
        }
    }
    
    private void loadComboBox(javax.swing.JComboBox theBox) {
        theBox.removeAllItems();
        int catIndex = convertionType.getSelectedIndex();
        ArrayList<Unit> currentList = unitCat.get(catIndex).unitList;
        for (int i = 0; i < currentList.size(); i++) {
            theBox.addItem(currentList.get(i).name);
        }
    }
    
    private void loadComboBox(javax.swing.JComboBox theBox,
            boolean cacheOrNah) {
        if (cacheOrNah) {
            int cacheIndex = theBox.getSelectedIndex();
            loadComboBox(theBox);
            theBox.setSelectedIndex(Math.min(theBox.getModel().getSize() - 1,
                    cacheIndex));
        } else {
            loadComboBox(theBox);
        }
    }
    
    private void loadListBox() {
        DefaultListModel model = new DefaultListModel();
        int catIndex = convertionType.getSelectedIndex();
        ArrayList<Unit> currentList = unitCat.get(catIndex).unitList;
        for (int i = 0; i < currentList.size(); i++) {
            model.addElement(getStringFromUnit(currentList.get(i)));
        }
        unitList.setModel(model);
    }
    
    private String getStringFromUnit(Unit u) {
        return u.name + ", " + u.coefficient + ", " + u.offset;
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        expresshun = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        resultBox = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        expresshun1 = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        graphingPanel = new javax.swing.JPanel(){
            public void paint(Graphics g) {
                super.paint(g);
                if (jTabbedPane1.getSelectedIndex() == 1)
                graphTheGraphyGraph();
            }
        };
        jLabel6 = new javax.swing.JLabel();
        jComboBox = new javax.swing.JComboBox();
        fromBox = new javax.swing.JTextField();
        toBox = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jPanel4 = new javax.swing.JPanel();
        convertTextField1 = new javax.swing.JTextField();
        unitComboBox1 = new javax.swing.JComboBox();
        convertTextField2 = new javax.swing.JTextField();
        unitComboBox2 = new javax.swing.JComboBox();
        jLabel10 = new javax.swing.JLabel();
        convertionType = new javax.swing.JComboBox();
        jScrollPane3 = new javax.swing.JScrollPane();
        unitList = new javax.swing.JList();
        unitNameTextField = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        unitCoefficientTextField = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        unitOffsetTextField = new javax.swing.JTextField();
        deleteUnitButton = new javax.swing.JButton();
        deleteClassButton = new javax.swing.JButton();
        classNameTextField = new javax.swing.JTextField();
        addUnitButton = new javax.swing.JButton();
        addClassButton = new javax.swing.JButton();
        saveUnitButton = new javax.swing.JButton();
        swapButton = new javax.swing.JButton();
        moveUpButton = new javax.swing.JButton();
        moveDownButton = new javax.swing.JButton();
        ResetButton = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        inputBox = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        inputBaseBox = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        outputBaseBox = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        resultBox1 = new javax.swing.JTextArea();
        checkbox1 = new java.awt.Checkbox();
        degreeButton = new javax.swing.JRadioButton();
        radianButton = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });
        jTabbedPane1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTabbedPane1MousePressed(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jTabbedPane1MouseReleased(evt);
            }
        });

        expresshun.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        expresshun.setText("0");
        expresshun.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                expresshunCaretUpdate(evt);
            }
        });

        resultBox.setEditable(false);
        resultBox.setColumns(20);
        resultBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        resultBox.setLineWrap(true);
        resultBox.setRows(5);
        resultBox.setWrapStyleWord(true);
        jScrollPane1.setViewportView(resultBox);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 546, Short.MAX_VALUE)
                    .addComponent(expresshun, javax.swing.GroupLayout.Alignment.TRAILING))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(expresshun, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 356, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Expresshun", jPanel1);

        expresshun1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        expresshun1.setText("x");
        expresshun1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                expresshun1CaretUpdate(evt);
            }
        });

        jLabel5.setText("y =");

        graphingPanel.setBackground(new java.awt.Color(255, 255, 255));
        graphingPanel.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        graphingPanel.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                graphingPanelMouseDragged(evt);
            }
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                graphingPanelMouseMoved(evt);
            }
        });
        graphingPanel.addMouseWheelListener(new java.awt.event.MouseWheelListener() {
            public void mouseWheelMoved(java.awt.event.MouseWheelEvent evt) {
                graphingPanelMouseWheelMoved(evt);
            }
        });
        graphingPanel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                graphingPanelMousePressed(evt);
            }
        });
        graphingPanel.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentResized(java.awt.event.ComponentEvent evt) {
                graphingPanelComponentResized(evt);
            }
        });
        graphingPanel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                graphingPanelKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                graphingPanelKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout graphingPanelLayout = new javax.swing.GroupLayout(graphingPanel);
        graphingPanel.setLayout(graphingPanelLayout);
        graphingPanelLayout.setHorizontalGroup(
            graphingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        graphingPanelLayout.setVerticalGroup(
            graphingPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 326, Short.MAX_VALUE)
        );

        jLabel6.setText("x:");

        jComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Rectangular", "Polar" }));
        jComboBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBoxActionPerformed(evt);
            }
        });

        fromBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        fromBox.setText("-inf");
        fromBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                fromBoxCaretUpdate(evt);
            }
        });

        toBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        toBox.setText("inf");
        toBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                toBoxCaretUpdate(evt);
            }
        });

        jLabel7.setText("[");

        jLabel8.setText(", ");

        jLabel9.setText("]");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(graphingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(expresshun1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromBox, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toBox, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel9))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(expresshun1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5)
                    .addComponent(fromBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(toBox, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(graphingPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jComboBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jTabbedPane1.addTab("Grapherico", jPanel3);

        convertTextField1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        convertTextField1.setText("1");
        convertTextField1.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                convertTextField1CaretUpdate(evt);
            }
        });

        unitComboBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        unitComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitComboBox1ActionPerformed(evt);
            }
        });

        convertTextField2.setEditable(false);
        convertTextField2.setBackground(new java.awt.Color(255, 255, 255));
        convertTextField2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N

        unitComboBox2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        unitComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitComboBox2ActionPerformed(evt);
            }
        });

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel10.setText("Becumz:");

        convertionType.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        convertionType.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                convertionTypeActionPerformed(evt);
            }
        });

        unitList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                unitListValueChanged(evt);
            }
        });
        jScrollPane3.setViewportView(unitList);

        unitNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitNameTextFieldActionPerformed(evt);
            }
        });

        jLabel11.setText("Name:");

        jLabel12.setText("Coefficient:");

        unitCoefficientTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitCoefficientTextFieldActionPerformed(evt);
            }
        });

        jLabel13.setText("Offset:");

        unitOffsetTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unitOffsetTextFieldActionPerformed(evt);
            }
        });

        deleteUnitButton.setText("Delete Unit");
        deleteUnitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteUnitButtonActionPerformed(evt);
            }
        });

        deleteClassButton.setText("Delete Class");
        deleteClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteClassButtonActionPerformed(evt);
            }
        });

        classNameTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                classNameTextFieldActionPerformed(evt);
            }
        });

        addUnitButton.setText("Add Unit");
        addUnitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addUnitButtonActionPerformed(evt);
            }
        });

        addClassButton.setText("Add Class");
        addClassButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addClassButtonActionPerformed(evt);
            }
        });

        saveUnitButton.setText("Save");
        saveUnitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveUnitButtonActionPerformed(evt);
            }
        });

        swapButton.setText("Swapsie");
        swapButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                swapButtonActionPerformed(evt);
            }
        });

        moveUpButton.setText("Up");
        moveUpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveUpButtonActionPerformed(evt);
            }
        });

        moveDownButton.setText("Down");
        moveDownButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveDownButtonActionPerformed(evt);
            }
        });

        ResetButton.setText("Reset");
        ResetButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ResetButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(swapButton))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addGroup(jPanel4Layout.createSequentialGroup()
                                    .addComponent(jLabel11)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(unitNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 249, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel12)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(unitCoefficientTextField))
                                    .addComponent(convertionType, javax.swing.GroupLayout.Alignment.TRAILING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(classNameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                        .addComponent(deleteUnitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(deleteClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel4Layout.createSequentialGroup()
                                        .addComponent(jLabel13)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(unitOffsetTextField)))
                                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                                    .addComponent(addUnitButton, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(addClassButton, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel4Layout.createSequentialGroup()
                                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(moveUpButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(moveDownButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(168, 168, 168)
                                .addComponent(saveUnitButton))
                            .addComponent(ResetButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(convertTextField2)
                            .addComponent(convertTextField1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(unitComboBox1, 0, 237, Short.MAX_VALUE)
                            .addComponent(unitComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(convertTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel10)
                    .addComponent(swapButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(convertTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(unitComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unitNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unitCoefficientTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel12))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(unitOffsetTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(saveUnitButton)
                            .addComponent(moveUpButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(moveDownButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(ResetButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(addClassButton)
                            .addComponent(addUnitButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(deleteClassButton)
                            .addComponent(deleteUnitButton))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(classNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(convertionType, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane3))
                .addContainerGap())
        );

        deleteUnitButton.getAccessibleContext().setAccessibleName("");

        jTabbedPane1.addTab("Scientific Convertshun", jPanel4);

        inputBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inputBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                inputBoxCaretUpdate(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setText("Input:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setText("Base:");

        inputBaseBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        inputBaseBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                inputBaseBoxCaretUpdate(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Output Base:");

        outputBaseBox.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        outputBaseBox.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                outputBaseBoxCaretUpdate(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel4.setText("Output:");

        resultBox1.setEditable(false);
        resultBox1.setColumns(20);
        resultBox1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        resultBox1.setLineWrap(true);
        resultBox1.setRows(5);
        resultBox1.setWrapStyleWord(true);
        jScrollPane2.setViewportView(resultBox1);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputBox))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(inputBaseBox, javax.swing.GroupLayout.DEFAULT_SIZE, 486, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(outputBaseBox, javax.swing.GroupLayout.DEFAULT_SIZE, 418, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(inputBaseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(outputBaseBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 260, Short.MAX_VALUE)
                .addContainerGap())
        );

        jTabbedPane1.addTab("Number Base Convertshun", jPanel2);

        checkbox1.setLabel("Keep me on top eh?");
        checkbox1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkbox1ItemStateChanged(evt);
            }
        });

        buttonGroup1.add(degreeButton);
        degreeButton.setSelected(true);
        degreeButton.setText("Degree");
        degreeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                degreeButtonActionPerformed(evt);
            }
        });

        buttonGroup1.add(radianButton);
        radianButton.setText("Radian");
        radianButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radianButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jTabbedPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(radianButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(degreeButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jTabbedPane1)
                .addGap(7, 7, 7)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(checkbox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(radianButton)
                        .addComponent(degreeButton)))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    
    public void paint(Graphics g) {
        super.paint(g);
        if (jTabbedPane1.getSelectedIndex() == 1)
            graphTheGraphyGraph();
    }
    private void updateEx() {
        if (jTabbedPane1.getSelectedIndex() == 1) {
            graphTheGraphyGraph();
        } else if (jTabbedPane1.getSelectedIndex() == 0) {
            try {
                BigDecimal result = ExpressionSlayer.macro(expresshun.getText()
                        .trim(), degreeButton.isSelected());
                String output = result.toPlainString();
                output = trimZeroes(output);
                resultBox.setText(output);
            } catch (Exception e){
//            resultBox.setText(e.toString());
                resultBox.setText("Error sumwhere in there");
            }
        }
    }
    
    private String trimZeroes(String original) {
        String output = original;
        while (output.endsWith("0")) {
            output = output.substring(0, output.length() - 1);
        }
        output = output.replaceAll("\\.$", "");
        return output;
    }
    
    private void expresshunCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_expresshunCaretUpdate
        updateEx();
    }//GEN-LAST:event_expresshunCaretUpdate
    
    private void checkbox1ItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkbox1ItemStateChanged
        this.setAlwaysOnTop(checkbox1.getState());
    }//GEN-LAST:event_checkbox1ItemStateChanged
    
    private void conFlippingVert(){
        try {
            if (!inputBaseBox.getText().trim().equals("1") &&
                    !outputBaseBox.getText().trim().equals("1")){
                int inputBase = Integer.parseInt(inputBaseBox.getText());
                String input = inputBox.getText().toUpperCase().replaceAll("\\.+", ".");
                String inputDec = "";
                boolean hasDecimal = input.contains(".");
                if (hasDecimal) {
                    while (input.endsWith("0")) {
                        input = input.substring(0, input.length() - 1);
                    }
                    inputDec = input.substring(input.indexOf('.') + 1);
                    input = input.substring(0, input.indexOf('.'));
                }
                long intermediate = 0;
                BigDecimal intermediateDec = BigDecimal.ZERO;
                long j = 0;
                for (long i = input.length() - 1; i >= 0; i--) {
                    long digit = input.charAt((int) i) - '0';
                    if (digit < 0 || digit > 9){
                        digit = input.charAt((int) i) - 'A' + 10;
                    }
                    intermediate += Math.pow(inputBase, j++) * digit;
                }
                if (hasDecimal)
                    j = -1;
                for (long i = 0; i < inputDec.length(); i++) {
                    long digit = inputDec.charAt((int) i) - '0';
                    if (digit < 0 || digit > 9){
                        digit = inputDec.charAt((int) i) - 'A' + 10;
                    }
                    intermediateDec = intermediateDec.add(
                            new BigDecimal(Math.pow(inputBase, j--) * digit));
                }
                int outputBase = Integer.parseInt(outputBaseBox.getText());
                String output = "";
                while (intermediate > 0) {
                    char theChar;
                    if (intermediate % outputBase < 10) {
                        theChar = (char) ('0' + intermediate % outputBase);
                    } else {
                        theChar = (char) (intermediate % outputBase - 10 + 'A');
                    }
                    output = theChar + output;
                    intermediate /= outputBase;
                }
                if (hasDecimal) {
                    output += ".";
                    for (int i = 1; i <= 20; i++) {
                        intermediateDec = intermediateDec.multiply(
                                new BigDecimal(outputBase));
                        char theChar;
                        if (intermediateDec.intValue() < 10) {
                            theChar = (char) ('0' + intermediateDec.intValue());
                        } else {
                            theChar = (char) (intermediateDec.intValue() - 10 + 'A');
                        }
                        output += theChar;
                        intermediateDec = intermediateDec.remainder(
                                BigDecimal.ONE);
                        if (intermediateDec.compareTo(BigDecimal.ZERO) == 0) break;
                    }
                }
                resultBox1.setText(output);
            }
        } catch(Exception e) {
            
        }
    }
    
    private void outputBaseBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_outputBaseBoxCaretUpdate
        conFlippingVert();
    }//GEN-LAST:event_outputBaseBoxCaretUpdate
    
    private void inputBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_inputBoxCaretUpdate
        conFlippingVert();
    }//GEN-LAST:event_inputBoxCaretUpdate
    
    private void inputBaseBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_inputBaseBoxCaretUpdate
        conFlippingVert();
    }//GEN-LAST:event_inputBaseBoxCaretUpdate
    
    private void radianButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radianButtonActionPerformed
        updateEx();
    }//GEN-LAST:event_radianButtonActionPerformed
    
    private void degreeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_degreeButtonActionPerformed
        updateEx();
    }//GEN-LAST:event_degreeButtonActionPerformed
    
    private void graphingPanelMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphingPanelMousePressed
        graphingPanel.requestFocusInWindow();
        if (SwingUtilities.isLeftMouseButton(evt)) {
            pressedX = evt.getX();
            pressedY = evt.getY();
            originalX = offX;
            originalY = offY;
        } else if (SwingUtilities.isMiddleMouseButton(evt)) {
            offX = 0;
            offY = 0;
            scaleX = 1;
            scaleY = 1;
            graphTheGraphyGraph();
            mouseMoveOnGraph(evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_graphingPanelMousePressed
    
    private void expresshun1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_expresshun1CaretUpdate
        graphTheGraphyGraph();
    }//GEN-LAST:event_expresshun1CaretUpdate
    
    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        if (jTabbedPane1.getSelectedIndex() == 1)
            graphTheGraphyGraph();
    }//GEN-LAST:event_jTabbedPane1StateChanged
    
    private void jTabbedPane1MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MouseReleased
        if (jTabbedPane1.getSelectedIndex() == 1)
            graphTheGraphyGraph();
    }//GEN-LAST:event_jTabbedPane1MouseReleased
    
    private void jTabbedPane1MousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTabbedPane1MousePressed
        if (jTabbedPane1.getSelectedIndex() == 1)
            graphTheGraphyGraph();
    }//GEN-LAST:event_jTabbedPane1MousePressed
    
    private void graphingPanelMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphingPanelMouseDragged
        graphingPanel.requestFocusInWindow();
        if (SwingUtilities.isLeftMouseButton(evt)) {
            offX = originalX + (evt.getX() - pressedX) / scaleX;
            offY = originalY + (evt.getY() - pressedY) / scaleY;
            graphTheGraphyGraph();
        }
    }//GEN-LAST:event_graphingPanelMouseDragged
    
    private void graphingPanelMouseWheelMoved(java.awt.event.MouseWheelEvent evt) {//GEN-FIRST:event_graphingPanelMouseWheelMoved
        if (evt.getWheelRotation() < 0) {
            if (ctrlDown) {
                scaleX *= ZOOM_MULTIPLIER;
            } else if (shiftDown) {
                scaleY *= ZOOM_MULTIPLIER;
            } else {
                scaleX *= ZOOM_MULTIPLIER;
                scaleY *= ZOOM_MULTIPLIER;
            }
            graphTheGraphyGraph();
        } else if (evt.getWheelRotation() > 0){
            if (ctrlDown) {
                if (scaleX / ZOOM_MULTIPLIER > 0.0001) {
                    scaleX /= ZOOM_MULTIPLIER;
                }
            } else if (shiftDown) {
                if (scaleY / ZOOM_MULTIPLIER > 0.0001) {
                    scaleY /= ZOOM_MULTIPLIER;
                }
            } else {
                if (Math.min(scaleX / ZOOM_MULTIPLIER,
                        scaleY / ZOOM_MULTIPLIER) > 0.0001){
                    scaleX /= ZOOM_MULTIPLIER;
                    scaleY /= ZOOM_MULTIPLIER;
                    
                }
            }
            graphTheGraphyGraph();
        }
        mouseMoveOnGraph(evt.getX(), evt.getY());
    }//GEN-LAST:event_graphingPanelMouseWheelMoved
    
    private void graphingPanelComponentResized(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_graphingPanelComponentResized
        offScrImg = createImage(graphingPanel.getWidth() - 5,
                graphingPanel.getHeight() - 5);
        offScrGrp = offScrImg.getGraphics();
    }//GEN-LAST:event_graphingPanelComponentResized
    
    private void graphingPanelKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_graphingPanelKeyPressed
        if (evt.getKeyCode() == 17) ctrlDown = true;
        else if (evt.getKeyCode() == 16) shiftDown = true;
    }//GEN-LAST:event_graphingPanelKeyPressed
    
    private void graphingPanelKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_graphingPanelKeyReleased
        ctrlDown = false;
        shiftDown = false;
    }//GEN-LAST:event_graphingPanelKeyReleased
    
    private void graphingPanelMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_graphingPanelMouseMoved
        mouseMoveOnGraph(evt.getX(), evt.getY());
    }//GEN-LAST:event_graphingPanelMouseMoved
    
    private void mouseMoveOnGraph(int x, int y) {
        if (jComboBox.getSelectedIndex() == 0) {
            String xLoc = String.format("%.4f", (x - 2
                    - (graphingPanel.getWidth() / 2 + offX * scaleX))
                    / (scaleX * DIVISION_LENGTH));
            String yLoc = String.format("%.4f", (
                    (graphingPanel.getHeight() / 2 + offY * scaleY)
                            - (y - 2))
                    / (scaleY * DIVISION_LENGTH));
            String zoomX = String.format("%.6f", (scaleX));
            String zoomY = String.format("%.6f", (scaleY));
            jLabel6.setText("X = " + xLoc + "; Y = " + yLoc
                    + "; X zoom: x" + zoomX + "; Y zoom: x" + zoomY);
            
            try {
                
                double realX = (physToGraphX(x));
                if (realX >= limitLo && realX <= limitHi) {
                    double realY = ExpressionSlayer.macro(expresshun1.getText().trim().
                            replaceAll("[xX]",
                                    new BigDecimal(realX).toPlainString()
                                            + ""), degreeButton.isSelected())
                            .doubleValue();
                    xLoc = String.format("%.4f", realX);
                    yLoc = String.format("%.4f", realY);
                    int displayY = graphToPhysY(realY);
                    Image newImg = createImage(graphingPanel.getWidth() - 5,
                            graphingPanel.getHeight() - 5);
                    Graphics newGraphics = newImg.getGraphics();
                    newGraphics.drawImage(offScrImg, 0, 0, graphingPanel);
                    newGraphics.setColor(GRAPH_LINES);
                    newGraphics.fillOval(x - 7, displayY - 7, 10, 10);
                    newGraphics.drawString(xLoc + ", " + yLoc,
                            x + 2, displayY + 4);
                    graphingPanel.getGraphics().drawImage(newImg, 2,
                            2, graphingPanel);
                }
            } catch (Exception e) {
                
            }
        } else if (jComboBox.getSelectedIndex() == 1) {
            double radAngle = Math.atan2(physToGraphY(y), physToGraphX(x));
            double degAngle = Math.toDegrees(radAngle);
            if (degAngle < 0) degAngle += 360;
            String angle = String.format("%.4f", degAngle) + "°";
            
            int centerX = (int) (graphingPanel.getWidth() / 2 + offX * scaleX);
            int centerY = (int) (graphingPanel.getHeight() / 2 + offY * scaleX);
            double distantFromCenter = Math.sqrt(Math.pow(x - centerX, 2)
                    + Math.pow(y - centerY, 2));
            int realX = (int) (centerX + Math.cos(radAngle) * distantFromCenter);
            int realY = (int) (centerY - Math.sin(radAngle) * distantFromCenter);
            while (realX >= 0 && realX <= graphingPanel.getWidth()
                    && realY >= 0 && realY <= graphingPanel.getHeight()) {
                realX = centerX + (realX - centerX) * 2;
                realY = centerY + (realY - centerY) * 2;
            }
            
            Image newImg = createImage(graphingPanel.getWidth() - 5,
                    graphingPanel.getHeight() - 5);
            Graphics newGraphics = newImg.getGraphics();
            newGraphics.drawImage(offScrImg, 0, 0, graphingPanel);
            newGraphics.setColor(GRAPH_LINES);
            newGraphics.drawLine(centerX, centerY, realX, realY);
            newGraphics.drawString(angle,
                    x + 2, y + 2);
            graphingPanel.getGraphics().drawImage(newImg, 2,
                    2, graphingPanel);
        }
    }
    
    private double physToGraphX (int x) {
        return (x - 2 - (graphingPanel.getWidth() / 2 + offX * scaleX))
                / (scaleX * DIVISION_LENGTH);
    }
    
    private double physToGraphY (int y) {
        return ((graphingPanel.getHeight() / 2 + offY * scaleY) - 2 - y)
                / (scaleY * DIVISION_LENGTH);
    }
    
    private int graphToPhysY (double y) {
        return (int) (- y * DIVISION_LENGTH * scaleY
                + graphingPanel.getHeight() / 2 + offY * scaleY + 2);
    }
    
    private void jComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBoxActionPerformed
        if (jTabbedPane1.getSelectedIndex() == 1)
            graphTheGraphyGraph();
        if (jComboBox.getSelectedIndex() == 0) {
            jLabel5.setText("y =");
        } else if (jComboBox.getSelectedIndex() == 1) {
            jLabel5.setText("r =");
        }
        graphTheGraphyGraph();
    }//GEN-LAST:event_jComboBoxActionPerformed

    private void fromBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_fromBoxCaretUpdate
        graphTheGraphyGraph();
    }//GEN-LAST:event_fromBoxCaretUpdate
    
    private void toBoxCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_toBoxCaretUpdate
        graphTheGraphyGraph();
    }//GEN-LAST:event_toBoxCaretUpdate

    private void unitOffsetTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitOffsetTextFieldActionPerformed
        unitChange(false);
    }//GEN-LAST:event_unitOffsetTextFieldActionPerformed

    private void convertTextField1CaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_convertTextField1CaretUpdate
        oneToTwo();
    }//GEN-LAST:event_convertTextField1CaretUpdate

    private void unitComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitComboBox1ActionPerformed
        oneToTwo();
    }//GEN-LAST:event_unitComboBox1ActionPerformed

    private void unitComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitComboBox2ActionPerformed
        oneToTwo();
    }//GEN-LAST:event_unitComboBox2ActionPerformed

    private void convertionTypeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_convertionTypeActionPerformed
        if (convertionType.getSelectedIndex() >= 0) {
            loadComboBox(unitComboBox1);
            loadComboBox(unitComboBox2);
            loadListBox();
            classNameTextField.setText(unitCat.
                    get(convertionType.getSelectedIndex()).name);
        }
    }//GEN-LAST:event_convertionTypeActionPerformed

    private void unitListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_unitListValueChanged
        if (unitList.getSelectedIndex() >= 0) {
            int catIndex = convertionType.getSelectedIndex();
            ArrayList<Unit> currentList = unitCat.get(catIndex).unitList;
            Unit selectedUnit = currentList.get(unitList.getSelectedIndex());
            unitNameTextField.setText(selectedUnit.name);
            unitCoefficientTextField.setText(selectedUnit.coefficient);
            unitOffsetTextField.setText(selectedUnit.offset);
        }
    }//GEN-LAST:event_unitListValueChanged

    private void unitNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitNameTextFieldActionPerformed
        unitChange(false);
    }//GEN-LAST:event_unitNameTextFieldActionPerformed

    private void unitCoefficientTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unitCoefficientTextFieldActionPerformed
        unitChange(false);
    }//GEN-LAST:event_unitCoefficientTextFieldActionPerformed

    private void saveUnitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveUnitButtonActionPerformed
        unitChange(false);
    }//GEN-LAST:event_saveUnitButtonActionPerformed

    private void classNameTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_classNameTextFieldActionPerformed
        unitChange(true);
    }//GEN-LAST:event_classNameTextFieldActionPerformed

    private void addUnitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addUnitButtonActionPerformed
        UnitList currentList = unitCat.get(convertionType.getSelectedIndex());
        Unit newUnit = new Unit("NewUnit", "1", "0");
        currentList.unitList.add(newUnit);
        loadListBox();
        unitList.setSelectedIndex(unitList.getModel().getSize() - 1);
        loadComboBox(unitComboBox1, true);
        loadComboBox(unitComboBox2, true);
        saveUnit();
    }//GEN-LAST:event_addUnitButtonActionPerformed

    private void swapButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_swapButtonActionPerformed
        int index1 = unitComboBox1.getSelectedIndex();
        int index2 = unitComboBox2.getSelectedIndex();
        unitComboBox1.setSelectedIndex(index2);
        unitComboBox2.setSelectedIndex(index1);
    }//GEN-LAST:event_swapButtonActionPerformed

    private void deleteUnitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteUnitButtonActionPerformed
        if (unitList.getSelectedIndex() >= 0) {
            UnitList currentList = unitCat.get(convertionType.getSelectedIndex());
            currentList.unitList.remove(unitList.getSelectedIndex());
            DefaultListModel model = (DefaultListModel) unitList.getModel();
            model.remove(unitList.getSelectedIndex());
            loadComboBox(unitComboBox1, true);
            loadComboBox(unitComboBox2, true);
            saveUnit();
        }
    }//GEN-LAST:event_deleteUnitButtonActionPerformed

    private void moveUpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveUpButtonActionPerformed
        if (unitList.getSelectedIndex() > 0) {
            swapUnit(unitList.getSelectedIndex(),
                    unitList.getSelectedIndex() - 1);
            unitList.setSelectedIndex(unitList.getSelectedIndex() - 1);
            loadComboBox(unitComboBox1, true);
            loadComboBox(unitComboBox2, true);
            saveUnit();
        }
    }//GEN-LAST:event_moveUpButtonActionPerformed
    
    private void swapUnit(int index1, int index2) {
        ArrayList theClass =
                unitCat.get(convertionType.getSelectedIndex()).unitList;
        Object temp = theClass.get(index1);
        theClass.set(index1, theClass.get(index2));
        theClass.set(index2, temp);
        DefaultListModel model = (DefaultListModel) unitList.getModel();
        String unit1 = unitList.getModel().getElementAt(index1).toString();
        String unit2 = unitList.getModel().getElementAt(index2).toString();
        model.setElementAt(unit2, index1);
        model.setElementAt(unit1, index2);
    }
    
    private void moveDownButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveDownButtonActionPerformed
        if (unitList.getSelectedIndex() < unitList.getModel().getSize() - 1) {
            swapUnit(unitList.getSelectedIndex(),
                    unitList.getSelectedIndex() + 1);
            unitList.setSelectedIndex(unitList.getSelectedIndex() + 1);
            loadComboBox(unitComboBox1, true);
            loadComboBox(unitComboBox2, true);
            saveUnit();
        }
    }//GEN-LAST:event_moveDownButtonActionPerformed

    private void addClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addClassButtonActionPerformed
        unitCat.add(new UnitList("NewClass"));
        loadComboBox();
        convertionType.setSelectedIndex(convertionType.getModel().getSize() - 1);
        saveUnit();
    }//GEN-LAST:event_addClassButtonActionPerformed

    private void deleteClassButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteClassButtonActionPerformed
        if (JOptionPane.showConfirmDialog(null,
                "Are you sure man? That's the whole class.",
                "Meguh Muth Muhsheen",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            if (convertionType.getSelectedIndex() >= 0) {
                int cacheIndex = convertionType.getSelectedIndex();
                unitCat.remove(convertionType.getSelectedIndex());
                loadComboBox();
                convertionType.setSelectedIndex(
                        Math.min(convertionType.getModel().getSize() - 1,
                                cacheIndex));
                saveUnit();
            }
        }
    }//GEN-LAST:event_deleteClassButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        saveUnit();
    }//GEN-LAST:event_formWindowClosing

    private void ResetButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ResetButtonActionPerformed
        if (JOptionPane.showConfirmDialog(null,
                "You know you're about the reset errythang right?",
                "Meguh Muth Muhsheen",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            unitCat = new ArrayList();
            try {
                loadUnitFromString(DEFAUL_UNITS);
            } catch (Exception ex) {}
            saveUnit();
        }
    }//GEN-LAST:event_ResetButtonActionPerformed
    
    private void unitChange(boolean classChange) {
        if (!classChange){
            if (unitList.getSelectedIndex() >= 0
                    && unitList.getSelectedIndex() < unitList.getModel().getSize()) {
                Unit theUnit = unitCat.get(convertionType.getSelectedIndex())
                        .unitList.get(unitList.getSelectedIndex());
                theUnit.name = unitNameTextField.getText();
                theUnit.coefficient = unitCoefficientTextField.getText();
                theUnit.offset = unitOffsetTextField.getText();
                DefaultListModel model = (DefaultListModel) unitList.getModel();
                model.setElementAt(getStringFromUnit(theUnit),
                        unitList.getSelectedIndex());
            }
        } else {
            if (convertionType.getSelectedIndex() >= 0) {
                UnitList theClass = unitCat.get(convertionType.getSelectedIndex());
                theClass.name = classNameTextField.getText();
                int cacheIndex = convertionType.getSelectedIndex();
                loadComboBox();
                convertionType.setSelectedIndex(cacheIndex);
            }
        }
        loadComboBox(unitComboBox1, true);
        loadComboBox(unitComboBox2, true);
        oneToTwo();
        saveUnit();
    }
    
    private void saveUnit() {
        try {
            File unitFile = new File("Unit.uni");
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(unitFile));
            String output = "";
            for (int i = 0; i < unitCat.size(); i++) {
                output += "<" + unitCat.get(i).name + ">\n";
                ArrayList<Unit> theList = unitCat.get(i).unitList;
                for (int j = 0; j < theList.size(); j++) {
                    output += getStringFromUnit(theList.get(j)) + "\n";
                }
                output += "</" + unitCat.get(i).name + ">\n";
            }
            fileWriter.write(output);
            fileWriter.close();
        } catch (Exception e) {}
    }
    
    private void oneToTwo() {
        oneToAnother(convertTextField1, convertTextField2,
                unitComboBox1, unitComboBox2);
    }
    
    private void oneToAnother(JTextField text1, JTextField text2
            , JComboBox combo1, JComboBox combo2) {
        try {
            int catIndex = convertionType.getSelectedIndex();
            int fromIndex = combo1.getSelectedIndex();
            int toIndex = combo2.getSelectedIndex();
            Unit from = unitCat.get(catIndex).unitList.get(fromIndex);
            Unit to = unitCat.get(catIndex).unitList.get(toIndex);
            BigDecimal intermediate = toIntermediate(text1.getText(),
                    from.coefficient,
                    from.offset);
            String inter = intermediate.toPlainString();
            BigDecimal result = reverseIntermediate(inter,
                    to.coefficient,
                    to.offset).setScale(15, RoundingMode.HALF_EVEN);
            text2.setText(trimZeroes(result.toPlainString()));
            jLabel10.setText("Becumz:");
        } catch (Exception e) {
            jLabel10.setText("- error brah -");
        }
    }
    
    private BigDecimal toIntermediate(String value,
            String coefficient,
            String offset) {
        BigDecimal result = ExpressionSlayer.macro(value, false);
        BigDecimal coeff = ExpressionSlayer.macro(coefficient, false);
        BigDecimal offs = ExpressionSlayer.macro(offset, false);
        result = result.subtract(offs);
        result = result.multiply(coeff);
        return result;
    }
    
    private BigDecimal reverseIntermediate(String value,
            String coefficient,
            String offset) {
        BigDecimal result = ExpressionSlayer.macro(value, false);
        BigDecimal coeff = ExpressionSlayer.macro(coefficient, false);
        BigDecimal offs = ExpressionSlayer.macro(offset, false);
        result = result.divide(coeff, RoundingMode.HALF_UP);
        result = result.add(offs);
        return result;
    }
    
    private void graphTheGraphyGraph() {
        if (jComboBox.getSelectedIndex() == 1) {
            scaleY = scaleX;
        }
        try {
            //Background
            offScrGrp.setColor(BACK_COLOR);
            offScrGrp.fillRect(0, 0, graphingPanel.getWidth(),
                    graphingPanel.getHeight());
            
            int centerX = (int) (graphingPanel.getWidth() / 2 + offX * scaleX);
            int centerY = (int) (graphingPanel.getHeight() / 2 + offY * scaleY);
            if (jComboBox.getSelectedIndex() == 0) {
                //The skinny lines
                
                int betterDivY = DIVISION_LENGTH;
                while (betterDivY * scaleY < 12) {
                    betterDivY *= 2;
                }
                offScrGrp.setColor(THIN_LINES);
                for (int y = (int) (centerY - betterDivY * scaleY);
                        y >= 0; y -= betterDivY * scaleY) {
                    offScrGrp.drawLine(0, y, graphingPanel.getWidth(), y);
                }
                
                for (int y = (int) (centerY + betterDivY * scaleY);
                        y <= graphingPanel.getHeight(); y += betterDivY * scaleY) {
                    offScrGrp.drawLine(0, y, graphingPanel.getWidth(), y);
                }
                
                int betterDivX = DIVISION_LENGTH;
                while (betterDivX * scaleX < 12) {
                    betterDivX *= 2;
                }
                for (int x = (int) (centerX + betterDivX * scaleX);
                        x <= graphingPanel.getWidth(); x += betterDivX * scaleX) {
                    offScrGrp.drawLine(x, 0, x, graphingPanel.getHeight());
                }
                for (int x = (int) (centerX - betterDivX * scaleX);
                        x >= 0; x -= betterDivX * scaleX) {
                    offScrGrp.drawLine(x, 0, x, graphingPanel.getHeight());
                }
                
                offScrGrp.setColor(BOLD_LINES);
                //Horizontal line
                offScrGrp.drawLine(0, centerY, graphingPanel.getWidth(),
                        centerY);
                
                //Vertical line
                offScrGrp.drawLine(centerX, 0,centerX,
                        graphingPanel.getHeight());
                
                //Graph it out
                int realY;
                boolean determined = false;
                offScrGrp.setColor(GRAPH_LINES);
                int previousX = -300, previousY = graphingPanel.getHeight() / 2;
                double graphX, graphY;
                limitLo = ExpressionSlayer.macro(fromBox.getText().trim()
                        , degreeButton.isSelected()).doubleValue();
                limitHi = ExpressionSlayer.macro(toBox.getText().trim()
                        , degreeButton.isSelected()).doubleValue();
                for (int x = 0; x < graphingPanel.getWidth(); x++) {
                    graphX = (x - centerX) / ((double) DIVISION_LENGTH * scaleX);
                    if (graphX >= limitLo && graphX <= limitHi) {
                        realY = previousY;
                        try {
                            graphY = ExpressionSlayer.macro(expresshun1.
                                    getText().trim().
                                    replaceAll("[xX]",
                                            new BigDecimal(graphX).toPlainString()
                                                    + "")
                                    , degreeButton.isSelected()).doubleValue();
                            realY = (int) (centerY
                                    - graphY * DIVISION_LENGTH * scaleY);
                            if (!determined) {
                                previousX = x;
                                previousY = realY;
                            }
                            determined = true;
                            offScrGrp.drawLine(previousX, previousY, x, realY);
                        } catch (Exception e) {
                            determined = false;
                        }
                        previousX = x;
                        previousY = realY;
                    }
                }
            } else {
                centerY = (int) (graphingPanel.getHeight() / 2 + offY * scaleX);
                //The thin circles
                offScrGrp.setColor(THIN_LINES);
                int corner1 = (int) Math.sqrt(centerX * centerX
                        + centerY * centerY);
                int corner2 = (int) Math.sqrt(
                        (graphingPanel.getWidth() - centerX)
                                * (graphingPanel.getWidth() - centerX)
                                + centerY * centerY);
                int corner3 = (int) Math.sqrt(
                        (graphingPanel.getWidth() - centerX)
                                * (graphingPanel.getWidth() - centerX)
                                + (graphingPanel.getHeight() - centerY)
                                        * (graphingPanel.getHeight() - centerY));
                int corner4 = (int) Math.sqrt(centerX * centerX
                        + (graphingPanel.getHeight() - centerY)
                                * (graphingPanel.getHeight() - centerY));
                int maxSize = Math.max(
                        Math.max(corner1, corner2),
                        Math.max(corner3, corner4));
                int betterDiv = DIVISION_LENGTH;
                while (betterDiv * scaleX < 12) {
                    betterDiv *= 2;
                }
                for (int i = (int) (betterDiv * scaleX); i <= maxSize;
                        i += betterDiv * scaleX) {
                    offScrGrp.drawOval(centerX - i, centerY - i,
                            i * 2, i * 2);
                }
                
                //The fat lines
                offScrGrp.setColor(BOLD_LINES);
                
                for (double i = 0; i <= Math.PI * 2; i+= Math.PI / 8) {
                    offScrGrp.drawLine(centerX, centerY,
                            centerX + (int) (maxSize * 2 * Math.cos(i))
                            , centerY - (int) (maxSize * 2 * Math.sin(i)));
                }
                
                offScrGrp.setColor(GRAPH_LINES);
                int previousX = centerX, previousY = centerY;
                boolean determined = false;
                double r;
                double interval = .01;
                int realX = centerX, realY = centerY;
                if (degreeButton.isSelected()) {
                    interval = .5;
                }
                limitLo = ExpressionSlayer.macro(fromBox.getText().trim()
                        , degreeButton.isSelected()).doubleValue();
                limitHi = ExpressionSlayer.macro(toBox.getText().trim()
                        , degreeButton.isSelected()).doubleValue();
                if ((limitHi - limitLo) / interval < 18849) {
                    for (double x = limitLo; x <= limitHi; x += interval) {
                        try {
                            r = ExpressionSlayer.macro(expresshun1.getText()
                                    .trim().replaceAll("[xX]",
                                            new BigDecimal(x).toPlainString()
                                                    + "")
                                    , degreeButton.isSelected()).doubleValue();
                            realX = centerX +
                                    (int) (r * Math.cos(x) * DIVISION_LENGTH * scaleX);
                            realY = centerY -
                                    (int) (r * Math.sin(x) * DIVISION_LENGTH * scaleX);
                            if (!determined) {
                                previousX = realX;
                                previousY = realY;
                            }
                            determined = true;
                            offScrGrp.drawLine(previousX, previousY, realX, realY);
                        } catch (Exception e) {
                            determined = false;
                        }
                        previousX = realX;
                        previousY = realY;
                    }
                } else {
                    offScrGrp.setColor(BACK_COLOR);
                    offScrGrp.fillRect(0, 0, graphingPanel.getWidth(),
                            graphingPanel.getHeight());
                    offScrGrp.setColor(Color.red);
                    offScrGrp.drawString("Bruh dat be too much fo me to do.",
                            10, 20);
                }
            }
            graphingPanel.getGraphics().drawImage(offScrImg, 2,
                    2, graphingPanel);
            
        } catch (Exception e) {
            try {
                Graphics panelGraphics = graphingPanel.getGraphics();
                panelGraphics.setColor(BACK_COLOR);
                panelGraphics.fillRect(0, 0, graphingPanel.getWidth(),
                        graphingPanel.getHeight());
                panelGraphics.setColor(Color.red);
                panelGraphics.drawString(e.toString(),
                        10, 20);
            } catch (Exception e2) {}
        }
    }
    
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
        * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
        */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(MMM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MMM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MMM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MMM.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MMM().setVisible(true);
            }
        });
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton ResetButton;
    private javax.swing.JButton addClassButton;
    private javax.swing.JButton addUnitButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private java.awt.Checkbox checkbox1;
    private javax.swing.JTextField classNameTextField;
    private javax.swing.JTextField convertTextField1;
    private javax.swing.JTextField convertTextField2;
    private javax.swing.JComboBox convertionType;
    private javax.swing.JRadioButton degreeButton;
    private javax.swing.JButton deleteClassButton;
    private javax.swing.JButton deleteUnitButton;
    private javax.swing.JTextField expresshun;
    private javax.swing.JTextField expresshun1;
    private javax.swing.JTextField fromBox;
    private javax.swing.JPanel graphingPanel;
    private javax.swing.JTextField inputBaseBox;
    private javax.swing.JTextField inputBox;
    private javax.swing.JComboBox jComboBox;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton moveDownButton;
    private javax.swing.JButton moveUpButton;
    private javax.swing.JTextField outputBaseBox;
    private javax.swing.JRadioButton radianButton;
    private javax.swing.JTextArea resultBox;
    private javax.swing.JTextArea resultBox1;
    private javax.swing.JButton saveUnitButton;
    private javax.swing.JButton swapButton;
    private javax.swing.JTextField toBox;
    private javax.swing.JTextField unitCoefficientTextField;
    private javax.swing.JComboBox unitComboBox1;
    private javax.swing.JComboBox unitComboBox2;
    private javax.swing.JList unitList;
    private javax.swing.JTextField unitNameTextField;
    private javax.swing.JTextField unitOffsetTextField;
    // End of variables declaration//GEN-END:variables
}
