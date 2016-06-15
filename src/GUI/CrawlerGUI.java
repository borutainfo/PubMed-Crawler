package GUI;

import Lucene.Lucene;
import javax.swing.JOptionPane;
import javax.swing.DefaultListModel;
import javax.swing.SwingWorker;
import java.awt.CardLayout;

import SQLite.DB;
import ParserXML.PubMed;
import Structs.Results;
import Structs.Summary;
import java.awt.Desktop;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;

public class CrawlerGUI extends javax.swing.JFrame {

    /* declarations */
    private final DB db;
    private final DefaultListModel exList;
    private final DefaultListModel searchList;
    private final DefaultListModel resultsList;
    private final CardLayout card;

    private Results results;
    private String url;
    private String journalURL;
    private boolean downloadPause = false;
    private int currentPage = 1;
    private String currentExpression = "";
    private int workers = 0;
    private int count = 0;
    private int done = 0;
    private boolean waiting = false;

    /* GUI constructor */
    public CrawlerGUI() {
        this.db = new DB();
        this.exList = new DefaultListModel();
        this.resultsList = new DefaultListModel();
        this.searchList = new DefaultListModel();

        initComponents();
        updateProgress();
        card = (CardLayout) getContentPane().getLayout();

        checkLuceneIndex();
        Lucene index = new Lucene(false);
        dbStateValueLabel.setText(Long.toString(index.getTotalSize()));
        index.close();

        logoLabel.setIcon(new ImageIcon(getClass().getResource("logo.png")));

        db.getExpressions().forEach(expression -> exList.addElement(expression));
        db.getExpressions(true).forEach(expression -> searchList.addElement(expression));
        downloadHistoryList.setModel(exList);
        searchResultsList.setModel(resultsList);
        searchHistoryList.setModel(searchList);

        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("icon.png")));

        lockResultsNavigation();

        searchDescriptionLabel.setText("<html>Aplikacja służy do pobierania i lokalnego przeglądania zawartości medycznej bazy danych PubMed.<br/>"
                + "Działa ona w dwóch trybach (możliwe jest jednoczesne działanie):<br/>"
                + "1. Pobieranie danych z PubMed'u<br/>"
                + "2. Przeszykiwanie lokalnej bazy<br/><br/>"
                + "Aby można było korzystać z trybu 2. należy najpierw pobrać dane dotyczące wybranej przez nas kategorii (tryb 1). Aby to zrobić przejdź do trybu \"Aktualizacja baza danych\" (możesz wybrać z menu) i wpisz wyrażenie wyszukiwania w bazie PubMed. Jeśli ilość wyników jest satysfakcjonująca naciśnij przycisk \"Pobierz\". Po pobraniu pierwszych dokumentów możesz korzystac z wyszukiwarki.<br/><br/>"
                + "Wyszukiwarka działa w oparciu o silnik Lucene. Można zatem korzystać tutaj z personalizowanych wyrażeń."
                + "<br/>Przykładowo wyrażenie: <i>title:cancer OR author:Michael</i> znajdzie wszystkie dokumenty, które zawierają słowo <i>cancer</i> w tytule lub pole autora zawiera imię <i>Michael</i>.<br/><br/>"
                + "<b>Dostępne pola:</b><br/>"
                + "- title (tytuł publikacji)<br/> "
                + "- abstract (streszczenie)<br/>"
                + "- author (autorzy)<br/>"
                + "- mesh (tagi MeSH)<br/>"
                + "- date (data wpisu PubMed - format DD/MM/YYY)<br/>"
                + "- journal (skrócona nazwa wydawnictwa)<br/>"
                + "- issn (międzynarodowy numer wydawnictwa)</html>");

        clearDownloadSearcher();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenuItem1 = new javax.swing.JMenuItem();
        firstFrame = new javax.swing.JPanel();
        logoLabel = new javax.swing.JLabel();
        searchLocalBaseButton = new javax.swing.JButton();
        updateLocalBaseButton = new javax.swing.JButton();
        dbStateLabel = new javax.swing.JLabel();
        dbStateValueLabel = new javax.swing.JLabel();
        helpFrame = new javax.swing.JPanel();
        quickStartLabel = new javax.swing.JLabel();
        quickStartSeparator1 = new javax.swing.JSeparator();
        searchDescriptionLabel = new javax.swing.JLabel();
        downloadModeFrame = new javax.swing.JPanel();
        downloadModeLabel = new javax.swing.JLabel();
        downloadModeSeparator1 = new javax.swing.JSeparator();
        searchPubMedLabel = new javax.swing.JLabel();
        searchPubMedTextField = new javax.swing.JTextField();
        searchPubMedButton = new javax.swing.JButton();
        resultCountLabel = new javax.swing.JLabel();
        resultCountValueLabel = new javax.swing.JLabel();
        downloadPubMedButton = new javax.swing.JButton();
        clearSearchButton = new javax.swing.JButton();
        downloadModeSeparator2 = new javax.swing.JSeparator();
        downloadHistoryLabel = new javax.swing.JLabel();
        downloadHistoryScrollPane = new javax.swing.JScrollPane();
        downloadHistoryList = new javax.swing.JList<>();
        updateAllTermsButton = new javax.swing.JButton();
        deleteSelectedButton = new javax.swing.JButton();
        deleteAllButton = new javax.swing.JButton();
        updateSelectedTermsButton = new javax.swing.JButton();
        downloadModeSeparator3 = new javax.swing.JSeparator();
        downloadModeSeparator4 = new javax.swing.JSeparator();
        downloadActionLabel = new javax.swing.JLabel();
        downloadProgressBar = new javax.swing.JProgressBar();
        downloadActionValueLabel = new javax.swing.JLabel();
        pauseDownloadButton = new javax.swing.JButton();
        stopDownloadButton = new javax.swing.JButton();
        searchModeFrame = new javax.swing.JPanel();
        searchModeSeparator1 = new javax.swing.JSeparator();
        searchModeLabel = new javax.swing.JLabel();
        searchExpressionLabel = new javax.swing.JLabel();
        searchExpressionTextField = new javax.swing.JTextField();
        searchExpressionButton = new javax.swing.JButton();
        searchResultsScrollPane = new javax.swing.JScrollPane();
        searchResultsList = new javax.swing.JList<>();
        searchSizeValueLabel = new javax.swing.JLabel();
        searchResultsNextButton = new javax.swing.JButton();
        searchResultsBackButton = new javax.swing.JButton();
        searchCurrentPageLabel = new javax.swing.JLabel();
        searchTotalSizeValueLabel = new javax.swing.JLabel();
        searchTotalSizeLabel = new javax.swing.JLabel();
        searchSizeLabel = new javax.swing.JLabel();
        currentPageLabel = new javax.swing.JLabel();
        searchModeSeparator2 = new javax.swing.JSeparator();
        searchHistoryScrollPane = new javax.swing.JScrollPane();
        searchHistoryList = new javax.swing.JList<>();
        searchHistoryLabel = new javax.swing.JLabel();
        searchSelectedButton = new javax.swing.JButton();
        clearHistoryButton = new javax.swing.JButton();
        searchResultsLabel = new javax.swing.JLabel();
        showSelectedResultButton = new javax.swing.JToggleButton();
        searchClearFormButton = new javax.swing.JButton();
        resultFrame = new javax.swing.JPanel();
        backToMainButton = new javax.swing.JButton();
        searchModeSeparator3 = new javax.swing.JSeparator();
        searchModeLabel1 = new javax.swing.JLabel();
        resultArticleTitleLabel = new javax.swing.JLabel();
        resultAuthorLabel = new javax.swing.JLabel();
        resultAuthorValueLabel = new javax.swing.JLabel();
        resultAbstractScrollPane = new javax.swing.JScrollPane();
        resultAbstractTextArea = new javax.swing.JTextArea();
        searchIdValueLabel = new javax.swing.JLabel();
        resultAbstractScrollPane1 = new javax.swing.JScrollPane();
        resultMeshTextArea = new javax.swing.JTextArea();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        resultRevisionDateLabel = new javax.swing.JLabel();
        resultRevisionDateValueLabel = new javax.swing.JLabel();
        resultJournalLabel = new javax.swing.JLabel();
        resultJournalValueLabel = new javax.swing.JLabel();
        mainMenu = new javax.swing.JMenuBar();
        startMenu = new javax.swing.JMenu();
        startPageItem = new javax.swing.JMenuItem();
        modeMenu = new javax.swing.JMenu();
        searchModeItem = new javax.swing.JMenuItem();
        downloadModeItem = new javax.swing.JMenuItem();
        helpMenu = new javax.swing.JMenu();
        quickStartMenuItem = new javax.swing.JMenuItem();
        aboutMenuItem = new javax.swing.JMenuItem();

        jMenuItem1.setText("jMenuItem1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("PubMed Crawler 1.0");
        setMaximumSize(new java.awt.Dimension(560, 420));
        setMinimumSize(new java.awt.Dimension(560, 420));
        setName("mainFrame"); // NOI18N
        setResizable(false);
        setSize(new java.awt.Dimension(560, 420));
        getContentPane().setLayout(new java.awt.CardLayout());

        searchLocalBaseButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        searchLocalBaseButton.setText("Szukaj publikacji");
        searchLocalBaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchLocalBaseButtonActionPerformed(evt);
            }
        });

        updateLocalBaseButton.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        updateLocalBaseButton.setText("Pobierz dane z bazy PubMed");
        updateLocalBaseButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateLocalBaseButtonActionPerformed(evt);
            }
        });

        dbStateLabel.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        dbStateLabel.setText("Dokumentów w lokalnej bazie:");

        dbStateValueLabel.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        dbStateValueLabel.setText("0");

        javax.swing.GroupLayout firstFrameLayout = new javax.swing.GroupLayout(firstFrame);
        firstFrame.setLayout(firstFrameLayout);
        firstFrameLayout.setHorizontalGroup(
            firstFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstFrameLayout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addGroup(firstFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(firstFrameLayout.createSequentialGroup()
                        .addComponent(dbStateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dbStateValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(firstFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(logoLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(searchLocalBaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, 500, Short.MAX_VALUE)
                        .addComponent(updateLocalBaseButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(40, Short.MAX_VALUE))
        );
        firstFrameLayout.setVerticalGroup(
            firstFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(firstFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(logoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(searchLocalBaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(updateLocalBaseButton, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(firstFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(dbStateLabel)
                    .addComponent(dbStateValueLabel))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        getContentPane().add(firstFrame, "first");

        quickStartLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        quickStartLabel.setText("PubMed Crawler 1.0");

        searchDescriptionLabel.setText("opis");

        javax.swing.GroupLayout helpFrameLayout = new javax.swing.GroupLayout(helpFrame);
        helpFrame.setLayout(helpFrameLayout);
        helpFrameLayout.setHorizontalGroup(
            helpFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(helpFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(quickStartSeparator1)
                    .addComponent(searchDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(quickStartLabel))
                .addContainerGap())
        );
        helpFrameLayout.setVerticalGroup(
            helpFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helpFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(quickStartLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(quickStartSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(searchDescriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 336, Short.MAX_VALUE)
                .addGap(23, 23, 23))
        );

        getContentPane().add(helpFrame, "start");

        downloadModeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        downloadModeLabel.setText("Aktualizcja lokalnej bazy danych");

        searchPubMedLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchPubMedLabel.setText("Szukaj w bazie PubMed:");

        searchPubMedTextField.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPubMedTextFieldActionPerformed(evt);
            }
        });

        searchPubMedButton.setText("Szukaj");
        searchPubMedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchPubMedButtonActionPerformed(evt);
            }
        });

        resultCountLabel.setText("Liczba dokumentów pasujących do wyrażenia:");

        resultCountValueLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        resultCountValueLabel.setText("0");

        downloadPubMedButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        downloadPubMedButton.setText("Pobierz");
        downloadPubMedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadPubMedButtonActionPerformed(evt);
            }
        });

        clearSearchButton.setText("Wyczyść");
        clearSearchButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearSearchButtonActionPerformed(evt);
            }
        });

        downloadHistoryLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        downloadHistoryLabel.setText("Historia pobieranych dokumentów:");

        downloadHistoryList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        downloadHistoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        downloadHistoryScrollPane.setViewportView(downloadHistoryList);

        updateAllTermsButton.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        updateAllTermsButton.setText("Aktualizuj wszystkie");
        updateAllTermsButton.setEnabled(false);
        updateAllTermsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateAllTermsButtonActionPerformed(evt);
            }
        });

        deleteSelectedButton.setText("Usuń wybrane wyrażenie z historii");
        deleteSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSelectedButtonActionPerformed(evt);
            }
        });

        deleteAllButton.setText("Usuń wszystkie wyrażenia z historii");
        deleteAllButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAllButtonActionPerformed(evt);
            }
        });

        updateSelectedTermsButton.setText("Aktualizuj wybrany");
        updateSelectedTermsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateSelectedTermsButtonActionPerformed(evt);
            }
        });

        downloadModeSeparator4.setToolTipText("");
        downloadModeSeparator4.setName(""); // NOI18N

        downloadActionLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        downloadActionLabel.setText("Status:");

        downloadActionValueLabel.setText("brak");

        pauseDownloadButton.setText("Wstrzymaj");
        pauseDownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pauseDownloadButtonActionPerformed(evt);
            }
        });

        stopDownloadButton.setText("Zakończ");
        stopDownloadButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                stopDownloadButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout downloadModeFrameLayout = new javax.swing.GroupLayout(downloadModeFrame);
        downloadModeFrame.setLayout(downloadModeFrameLayout);
        downloadModeFrameLayout.setHorizontalGroup(
            downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(downloadModeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(downloadProgressBar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(downloadModeSeparator4)
                    .addComponent(downloadModeSeparator1)
                    .addComponent(downloadModeSeparator2)
                    .addGroup(downloadModeFrameLayout.createSequentialGroup()
                        .addComponent(downloadHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 327, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(updateAllTermsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(deleteAllButton, javax.swing.GroupLayout.DEFAULT_SIZE, 222, Short.MAX_VALUE)
                            .addComponent(updateSelectedTermsButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(downloadModeSeparator3)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, downloadModeFrameLayout.createSequentialGroup()
                        .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(downloadModeFrameLayout.createSequentialGroup()
                                .addComponent(searchPubMedLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchPubMedTextField))
                            .addGroup(downloadModeFrameLayout.createSequentialGroup()
                                .addComponent(resultCountLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(resultCountValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(clearSearchButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(downloadPubMedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(searchPubMedButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, downloadModeFrameLayout.createSequentialGroup()
                        .addComponent(downloadActionLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(downloadActionValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pauseDownloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(stopDownloadButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(downloadModeFrameLayout.createSequentialGroup()
                        .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(downloadModeLabel)
                            .addComponent(downloadHistoryLabel))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        downloadModeFrameLayout.setVerticalGroup(
            downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(downloadModeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(downloadModeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadModeSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchPubMedLabel)
                    .addComponent(searchPubMedTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(searchPubMedButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultCountValueLabel)
                    .addComponent(resultCountLabel)
                    .addComponent(downloadPubMedButton)
                    .addComponent(clearSearchButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadModeSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadHistoryLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(downloadModeFrameLayout.createSequentialGroup()
                        .addComponent(updateAllTermsButton)
                        .addGap(9, 9, 9)
                        .addComponent(updateSelectedTermsButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(downloadModeSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteSelectedButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deleteAllButton))
                    .addComponent(downloadHistoryScrollPane))
                .addGap(13, 13, 13)
                .addComponent(downloadModeSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(downloadModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pauseDownloadButton)
                    .addComponent(stopDownloadButton)
                    .addComponent(downloadActionLabel)
                    .addComponent(downloadActionValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(downloadProgressBar, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(downloadModeFrame, "download");

        searchModeLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchModeLabel.setText("Przeszukiwanie lokalnej bazy");

        searchExpressionLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchExpressionLabel.setText("Wyszukiwarka:");

        searchExpressionButton.setText("Szukaj");
        searchExpressionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchExpressionButtonActionPerformed(evt);
            }
        });

        searchResultsList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        searchResultsList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        searchResultsScrollPane.setViewportView(searchResultsList);

        searchSizeValueLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchSizeValueLabel.setText("0");

        searchResultsNextButton.setText("Dalej ->");
        searchResultsNextButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchResultsNextButtonActionPerformed(evt);
            }
        });

        searchResultsBackButton.setText("<- Wstecz");
        searchResultsBackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchResultsBackButtonActionPerformed(evt);
            }
        });

        searchCurrentPageLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchCurrentPageLabel.setText("0");

        searchTotalSizeValueLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchTotalSizeValueLabel.setText("0");

        searchTotalSizeLabel.setText("Znalezionych dokumentów:");

        searchSizeLabel.setText("Wyników na stronie:");

        currentPageLabel.setText("Bieżąca strona:");

        searchHistoryList.setModel(new javax.swing.AbstractListModel<String>() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public String getElementAt(int i) { return strings[i]; }
        });
        searchHistoryList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        searchHistoryScrollPane.setViewportView(searchHistoryList);

        searchHistoryLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchHistoryLabel.setText("Historia zapytań:");

        searchSelectedButton.setText("Pokaż wyniki");
        searchSelectedButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchSelectedButtonActionPerformed(evt);
            }
        });

        clearHistoryButton.setText("Usuń historię");
        clearHistoryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearHistoryButtonActionPerformed(evt);
            }
        });

        searchResultsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        searchResultsLabel.setText("Wyniki wyszukiwania:");

        showSelectedResultButton.setText("Pokaż wybrany dokument");
        showSelectedResultButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSelectedResultButtonActionPerformed(evt);
            }
        });

        searchClearFormButton.setText("Wyczyść");
        searchClearFormButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchClearFormButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout searchModeFrameLayout = new javax.swing.GroupLayout(searchModeFrame);
        searchModeFrame.setLayout(searchModeFrameLayout);
        searchModeFrameLayout.setHorizontalGroup(
            searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchModeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchResultsScrollPane)
                    .addComponent(searchModeSeparator2)
                    .addComponent(searchModeSeparator1)
                    .addGroup(searchModeFrameLayout.createSequentialGroup()
                        .addComponent(searchTotalSizeLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchTotalSizeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(currentPageLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchCurrentPageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addComponent(searchSizeLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchSizeValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(searchModeFrameLayout.createSequentialGroup()
                        .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(searchModeFrameLayout.createSequentialGroup()
                                .addComponent(searchResultsBackButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(showSelectedResultButton, javax.swing.GroupLayout.PREFERRED_SIZE, 335, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(searchResultsNextButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(searchModeLabel)
                            .addComponent(searchResultsLabel)
                            .addGroup(searchModeFrameLayout.createSequentialGroup()
                                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addGroup(searchModeFrameLayout.createSequentialGroup()
                                            .addComponent(searchExpressionLabel)
                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                            .addComponent(searchExpressionTextField))
                                        .addComponent(searchHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(searchHistoryLabel))
                                .addGap(10, 10, 10)
                                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(clearHistoryButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(searchExpressionButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(searchSelectedButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                                    .addComponent(searchClearFormButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        searchModeFrameLayout.setVerticalGroup(
            searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(searchModeFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(searchModeLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(searchModeFrameLayout.createSequentialGroup()
                        .addComponent(searchModeSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(searchExpressionLabel)
                            .addComponent(searchExpressionTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(searchExpressionButton))
                        .addGap(14, 14, 14)
                        .addComponent(searchHistoryLabel))
                    .addComponent(searchClearFormButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(searchModeFrameLayout.createSequentialGroup()
                        .addComponent(searchSelectedButton)
                        .addGap(7, 7, 7)
                        .addComponent(clearHistoryButton))
                    .addComponent(searchHistoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(searchModeSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5)
                .addComponent(searchResultsLabel)
                .addGap(11, 11, 11)
                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchTotalSizeLabel)
                    .addComponent(searchTotalSizeValueLabel)
                    .addComponent(searchSizeLabel)
                    .addComponent(searchSizeValueLabel)
                    .addComponent(currentPageLabel)
                    .addComponent(searchCurrentPageLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchResultsScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(searchModeFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchResultsBackButton)
                    .addComponent(searchResultsNextButton)
                    .addComponent(showSelectedResultButton)))
        );

        getContentPane().add(searchModeFrame, "search");

        backToMainButton.setText("Wróć");
        backToMainButton.setPreferredSize(new java.awt.Dimension(83, 23));
        backToMainButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backToMainButtonActionPerformed(evt);
            }
        });

        searchModeLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchModeLabel1.setText("Podgląd dokumentu");

        resultArticleTitleLabel.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        resultArticleTitleLabel.setText("tytuł");
        resultArticleTitleLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultAuthorLabel.setText("Autorzy:");
        resultAuthorLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultAuthorValueLabel.setText("brak informacji");
        resultAuthorValueLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);

        resultAbstractTextArea.setColumns(20);
        resultAbstractTextArea.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        resultAbstractTextArea.setLineWrap(true);
        resultAbstractTextArea.setRows(5);
        resultAbstractTextArea.setAutoscrolls(false);
        resultAbstractTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        resultAbstractTextArea.setEnabled(false);
        resultAbstractScrollPane.setViewportView(resultAbstractTextArea);

        searchIdValueLabel.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        searchIdValueLabel.setText("23232323");
        searchIdValueLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchIdValueLabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        searchIdValueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchIdValueLabelMouseClicked(evt);
            }
        });

        resultMeshTextArea.setColumns(20);
        resultMeshTextArea.setFont(new java.awt.Font("Tahoma", 0, 10)); // NOI18N
        resultMeshTextArea.setLineWrap(true);
        resultMeshTextArea.setRows(5);
        resultMeshTextArea.setAutoscrolls(false);
        resultMeshTextArea.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        resultMeshTextArea.setEnabled(false);
        resultAbstractScrollPane1.setViewportView(resultMeshTextArea);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel1.setText("Wyrażenia MeSH:");

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        jLabel2.setText("Streszczenie:");

        resultRevisionDateLabel.setText("Data wpisu PubMed:");

        resultRevisionDateValueLabel.setText("01/02/2016");

        resultJournalLabel.setText("Wydawnictwo:");

        resultJournalValueLabel.setText("wydawca");
        resultJournalValueLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        resultJournalValueLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                resultJournalValueLabelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout resultFrameLayout = new javax.swing.GroupLayout(resultFrame);
        resultFrame.setLayout(resultFrameLayout);
        resultFrameLayout.setHorizontalGroup(
            resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(resultFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(resultAbstractScrollPane)
                    .addComponent(searchModeSeparator3)
                    .addComponent(resultArticleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultFrameLayout.createSequentialGroup()
                        .addComponent(searchModeLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 244, Short.MAX_VALUE)
                        .addComponent(searchIdValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(resultFrameLayout.createSequentialGroup()
                        .addComponent(resultAuthorLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultAuthorValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(resultAbstractScrollPane1)
                    .addComponent(backToMainButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(resultFrameLayout.createSequentialGroup()
                        .addGroup(resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(resultFrameLayout.createSequentialGroup()
                        .addComponent(resultRevisionDateLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultRevisionDateValueLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultJournalLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(resultJournalValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        resultFrameLayout.setVerticalGroup(
            resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, resultFrameLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchModeLabel1)
                    .addComponent(searchIdValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(searchModeSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultArticleTitleLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 55, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(resultAuthorValueLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 33, Short.MAX_VALUE)
                    .addComponent(resultAuthorLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(3, 3, 3)
                .addGroup(resultFrameLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(resultRevisionDateLabel)
                    .addComponent(resultRevisionDateValueLabel)
                    .addComponent(resultJournalLabel)
                    .addComponent(resultJournalValueLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultAbstractScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(resultAbstractScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(backToMainButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        getContentPane().add(resultFrame, "result");

        mainMenu.setPreferredSize(new java.awt.Dimension(74, 20));

        startMenu.setText("Start");

        startPageItem.setText("Pokaż stronę startową");
        startPageItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                startPageItemActionPerformed(evt);
            }
        });
        startMenu.add(startPageItem);

        mainMenu.add(startMenu);

        modeMenu.setText("Tryb");

        searchModeItem.setText("Przeszukiwania lokalnej bazy");
        searchModeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchModeItemActionPerformed(evt);
            }
        });
        modeMenu.add(searchModeItem);

        downloadModeItem.setText("Aktualizacja bazy danych");
        downloadModeItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                downloadModeItemActionPerformed(evt);
            }
        });
        modeMenu.add(downloadModeItem);

        mainMenu.add(modeMenu);

        helpMenu.setText("Pomoc");

        quickStartMenuItem.setText("Pomoc");
        quickStartMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                quickStartMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(quickStartMenuItem);

        aboutMenuItem.setText("O autorze");
        aboutMenuItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutMenuItemActionPerformed(evt);
            }
        });
        helpMenu.add(aboutMenuItem);

        mainMenu.add(helpMenu);

        setJMenuBar(mainMenu);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /* update progress bar and status label */
    private void updateProgress() {
        if (workers > 0) {
            lockDownloadSearcher();
            int percent = 0;
            if (count != 0) {
                percent = (int) Math.round((done * 100 / count));
            }
            downloadActionValueLabel.setText("pobieranie " + Integer.toString(percent) + "% ( " + Integer.toString(done) + " / " + Integer.toString(count) + " )");
            downloadProgressBar.setValue(percent);
        } else if (!waiting) {
            unlockDownloadSearcher();
            downloadActionValueLabel.setText("brak");
            downloadProgressBar.setValue(0);
            pauseDownloadButton.setEnabled(false);
            stopDownloadButton.setEnabled(false);
        }

    }

    /* get data from PubMed DB for specific expression */
    private void getDataFromPubMed(String expression) {
        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>() {
            PubMed pubmed = new PubMed(db);
            int toDo = 0;
            int range = 25;
            int retstart = 0;

            @Override
            protected Void doInBackground() throws Exception {
                lockDownloadSearcher();
                while (new File("lucene_data/write.lock").exists()) {
                    waiting = true;
                    Thread.sleep(500);
                }
                count += pubmed.getResultSize(expression);
                waiting = false;
                workers++;
                do {
                    if (!downloadPause) {
                        updateProgress();
                        toDo = pubmed.getResults(expression, retstart, range);
                        retstart += range;
                        done += range;
                    } else {
                        Thread.sleep(500);
                    }
                } while (toDo > 0 && workers > 0);
                return null;
            }

            @Override
            protected void done() {
                workers--;
                if (workers <= 0) {
                    workers = 0;
                    count = 0;
                    done = 0;
                }
                if (!downloadPause) {
                    updateProgress();
                }
            }
        };
        worker.execute();
    }

    /* get search results size from PubMed */
    private void getSearchSizeFromPubMed(String expression) {
        SwingWorker<Void, Void> worker;
        worker = new SwingWorker<Void, Void>() {
            int size = 0;
            PubMed pubmed = new PubMed(db);

            @Override
            protected Void doInBackground() throws Exception {
                size = pubmed.getResultSize(expression);
                return null;
            }

            @Override
            protected void done() {
                updateDownloadSearchResults(size);
            }
        };
        worker.execute();
    }

    private void showSummary(Summary summary) {
        card.show(getContentPane(), "result");
        resultArticleTitleLabel.setText(String.format("<html><div WIDTH=%d>%s</div><html>", 555, summary.title));
        String temp = "";
        temp = summary.author.stream().map((a) -> a + ", ").reduce(temp, String::concat);
        if (temp.length() > 2) {
            resultAuthorValueLabel.setText(String.format("<html><div WIDTH=%d>%s</div><html>", 507, temp.substring(0, temp.length() - 2)));
        }

        temp = "";
        temp = summary.text.stream().map((a) -> a + "\n\n").reduce(temp, String::concat);
        if (temp.length() > 2) {
            resultAbstractTextArea.setText(temp.substring(0, temp.length() - 2));
        }
        resultAbstractTextArea.setCaretPosition(0);

        temp = "";
        temp = summary.mesh.stream().map((a) -> a + ", ").reduce(temp, String::concat);
        if (temp.length() > 2) {
            resultMeshTextArea.setText(temp.substring(0, temp.length() - 2));
        }
        resultMeshTextArea.setCaretPosition(0);

        resultJournalValueLabel.setText("<html><a href=\"" + this.url + "\">" + summary.journal + "</a></html>");

        resultRevisionDateValueLabel.setText(summary.date);

        this.journalURL = "http://www.issn.cc/" + summary.issn;
        this.url = "http://www.ncbi.nlm.nih.gov/pubmed/" + Integer.toString(summary.id);
        searchIdValueLabel.setText("<html><a href=\"" + this.url + "\">" + Integer.toString(summary.id) + "</a></html>");
    }

    public void openWebpage(URL url) {
        try {
            URI uri = url.toURI();
            Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
            if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
                try {
                    desktop.browse(uri);
                } catch (IOException ex) {
                    Logger.getLogger(CrawlerGUI.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } catch (URISyntaxException ex) {
            Logger.getLogger(CrawlerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void checkLuceneIndex() {
        if (!(new File("lucene_data/").exists())) {
            Lucene index = new Lucene();
            index.close();
        }
        if (new File("lucene_data/write.lock").exists()) {
            new File("lucene_data/write.lock").delete();
        }
    }

    /* clear search field and reset results (download frame) */
    private void clearDownloadSearcher() {
        searchPubMedTextField.setText("");
        searchPubMedTextField.setEnabled(true);
        searchPubMedButton.setEnabled(true);
        resetDownloadSearchResults();
    }

    /* reset search results (download frame) */
    private void resetDownloadSearchResults() {
        downloadPubMedButton.setEnabled(false);
        resultCountValueLabel.setText("0");
    }

    /* update search results (download frame) */
    private void updateDownloadSearchResults(int size) {
        resultCountValueLabel.setText(Integer.toString(size));
        downloadPubMedButton.setEnabled(true);
        searchPubMedTextField.setEnabled(false);
        searchPubMedButton.setEnabled(false);
    }

    /* lock searcher when downloading data (download frame) */
    private void lockDownloadSearcher() {
        pauseDownloadButton.setEnabled(true);
        stopDownloadButton.setEnabled(true);
        resetDownloadSearchResults();
        searchPubMedTextField.setEnabled(false);
        searchPubMedButton.setEnabled(false);
        clearSearchButton.setEnabled(false);
        updateAllTermsButton.setEnabled(false);
        updateSelectedTermsButton.setEnabled(false);
        deleteSelectedButton.setEnabled(false);
        deleteAllButton.setEnabled(false);
    }

    /* lock searcher when not downloading data (download frame) */
    private void unlockDownloadSearcher() {
        clearDownloadSearcher();
        clearSearchButton.setEnabled(true);
        //updateAllTermsButton.setEnabled(true);
        updateSelectedTermsButton.setEnabled(true);
        deleteSelectedButton.setEnabled(true);
        deleteAllButton.setEnabled(true);
    }

    /* clear search results (search frame) */
    private void clearSearchResults() {
        resultsList.clear();
        searchCurrentPageLabel.setText("0");
        searchSizeValueLabel.setText("0");
    }

    /* search in database */
    private Results searchLucene() {
        return this.searchLucene(1);
    }

    private Results searchLucene(int page) {
        clearSearchResults();
        searchExpressionTextField.setText(currentExpression);
        Lucene index = new Lucene(false);
        Results res = index.search(currentExpression, page);
        if (res.totalSize > 0) {
            unlockResultsNavigation();
        } else {
            lockResultsNavigation();
        }

        searchSizeValueLabel.setText(Integer.toString(res.size));
        searchTotalSizeValueLabel.setText(Long.toString(res.totalSize));
        if (res.size >= 1) {
            searchCurrentPageLabel.setText(Integer.toString(page));
            res.summary.stream().forEach((summary) -> {
                resultsList.addElement(summary.title);
            });
            this.currentPage = res.page;
        } else if (page > 1) {
            this.results = searchLucene(page - 1);
        }
        return res;
    }

    private void lockResultsNavigation() {
        searchResultsBackButton.setEnabled(false);
        showSelectedResultButton.setEnabled(false);
        searchResultsNextButton.setEnabled(false);
        searchCurrentPageLabel.setText("0");
    }

    private void unlockResultsNavigation() {
        searchResultsBackButton.setEnabled(true);
        showSelectedResultButton.setEnabled(true);
        searchResultsNextButton.setEnabled(true);
    }

    /* show information about app author */
    private void aboutMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutMenuItemActionPerformed
        JOptionPane.showMessageDialog(this, "Autorem aplikacji jest Sebastian Boruta (sebastian@boruta.info).", "O autorze", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_aboutMenuItemActionPerformed


    private void backToMainButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backToMainButtonActionPerformed
        card.show(getContentPane(), "search");
    }//GEN-LAST:event_backToMainButtonActionPerformed

    /* search button has been clicked (download mode) */
    private void searchPubMedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPubMedButtonActionPerformed
        String expression = searchPubMedTextField.getText();
        if (!expression.equals("")) {
            resetDownloadSearchResults();
            searchPubMedTextField.setEnabled(false);
            searchPubMedButton.setEnabled(false);
            resultCountValueLabel.setText("czekaj");
            getSearchSizeFromPubMed(expression);
        } else {
            JOptionPane.showMessageDialog(this, "Wpisz prawidłowe wyrażenie!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchPubMedButtonActionPerformed

    private void searchPubMedTextFieldActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchPubMedTextFieldActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_searchPubMedTextFieldActionPerformed

    /* download button has been clicked (download mode) */
    private void downloadPubMedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadPubMedButtonActionPerformed
        String expression = searchPubMedTextField.getText();
        if (!expression.equals("")) {
            if (db.addExpression(expression)) {
                exList.add(0, expression);
            }
            clearDownloadSearcher();
            downloadActionValueLabel.setText("czekaj");
            getDataFromPubMed(expression);
        } else {
            JOptionPane.showMessageDialog(this, "Wystąpił błąd. Spróbuj ponownie!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_downloadPubMedButtonActionPerformed

    /* clear searcher button has been clicked (download mode) */
    private void clearSearchButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearSearchButtonActionPerformed
        clearDownloadSearcher();
    }//GEN-LAST:event_clearSearchButtonActionPerformed

    /* "update all items" button has been clicked (download mode) */
    private void updateAllTermsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateAllTermsButtonActionPerformed
        for (int i = 0; i < downloadHistoryList.getModel().getSize(); i++) {
            getDataFromPubMed(downloadHistoryList.getModel().getElementAt(i));
        }
    }//GEN-LAST:event_updateAllTermsButtonActionPerformed

    /* pause downloading button has been clicked (download mode) */
    private void pauseDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pauseDownloadButtonActionPerformed
        if (pauseDownloadButton.getText().equals("Wstrzymaj")) {
            downloadPause = true;
            downloadActionValueLabel.setText("zatrzymano");
            pauseDownloadButton.setText("Wznów");
        } else {
            downloadPause = false;
            downloadActionValueLabel.setText("wznawianie");
            pauseDownloadButton.setText("Wstrzymaj");
        }
    }//GEN-LAST:event_pauseDownloadButtonActionPerformed

    /* stop downloading button has been clicked (download mode) */
    private void stopDownloadButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_stopDownloadButtonActionPerformed
        workers = 0;
        downloadPause = false;
        pauseDownloadButton.setText("Wstrzymaj");
        updateProgress();
    }//GEN-LAST:event_stopDownloadButtonActionPerformed

    /* download mode has been choosen in menu */
    private void downloadModeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_downloadModeItemActionPerformed
        card.show(getContentPane(), "download");
    }//GEN-LAST:event_downloadModeItemActionPerformed

    /* "delete selected item" button has been clicked (download mode) */
    private void deleteSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSelectedButtonActionPerformed
        if (!downloadHistoryList.isSelectionEmpty()) {
            if (JOptionPane.showConfirmDialog(null, "Jesteś pewny, że chcesz usunąć wybrany element?", "Usunięcie elementu", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
                if (db.deleteExpression(downloadHistoryList.getSelectedValue())) {
                    exList.remove(downloadHistoryList.getSelectedIndex());
                } else {
                    JOptionPane.showMessageDialog(this, "Wystąpił błąd. Spróbuj ponownie!", "Błąd", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else {
            JOptionPane.showMessageDialog(this, "Musisz wybrać element!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_deleteSelectedButtonActionPerformed

    /* "delete all items" button has been clicked (download mode) */
    private void deleteAllButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteAllButtonActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Jesteś pewny, że chcesz usunąć wszystkie elementy?", "Usunięcie elementów", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            for (int i = 0; i < exList.getSize(); i++) {
                db.deleteExpression(exList.getElementAt(i).toString());
            }
            exList.clear();
        }
    }//GEN-LAST:event_deleteAllButtonActionPerformed

    /* "update selected item" button has been clicked (download mode) */
    private void updateSelectedTermsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateSelectedTermsButtonActionPerformed
        if (!downloadHistoryList.isSelectionEmpty()) {
            String expression = downloadHistoryList.getSelectedValue();
            downloadActionValueLabel.setText("czekaj");
            getDataFromPubMed(expression);
        } else {
            JOptionPane.showMessageDialog(this, "Musisz wybrać element!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_updateSelectedTermsButtonActionPerformed

    /* search mode has been choosen in menu */
    private void searchModeItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchModeItemActionPerformed
        card.show(getContentPane(), "search");
    }//GEN-LAST:event_searchModeItemActionPerformed

    /* search button has been clicked (search mode) */
    private void searchExpressionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchExpressionButtonActionPerformed
        if (!searchExpressionTextField.getText().equals("")) {
            currentExpression = searchExpressionTextField.getText();
            if (db.addExpression(currentExpression, true)) {
                searchList.add(0, currentExpression);
            }
            this.results = searchLucene();
        } else {
            JOptionPane.showMessageDialog(this, "Wpisz prawidłowe wyrażenie!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }

    }//GEN-LAST:event_searchExpressionButtonActionPerformed

    /* "next" button has been clicked (search mode) */
    private void searchResultsNextButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchResultsNextButtonActionPerformed
        this.results = searchLucene(this.currentPage + 1);
    }//GEN-LAST:event_searchResultsNextButtonActionPerformed

    /* "back" button has been clicked (search mode) */
    private void searchResultsBackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchResultsBackButtonActionPerformed
        this.results = searchLucene(((this.currentPage - 1) >= 1) ? (this.currentPage - 1) : 1);
    }//GEN-LAST:event_searchResultsBackButtonActionPerformed

    /* quick start item has been choosen in menu */
    private void quickStartMenuItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_quickStartMenuItemActionPerformed
        card.show(getContentPane(), "start");
    }//GEN-LAST:event_quickStartMenuItemActionPerformed

    /* search mode has been choosen from quick start */
    private void searchLocalBaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchLocalBaseButtonActionPerformed
        card.show(getContentPane(), "search");
    }//GEN-LAST:event_searchLocalBaseButtonActionPerformed

    /* download mode has been choosen in menu */
    private void updateLocalBaseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateLocalBaseButtonActionPerformed
        card.show(getContentPane(), "download");
    }//GEN-LAST:event_updateLocalBaseButtonActionPerformed

    private void clearHistoryButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearHistoryButtonActionPerformed
        if (JOptionPane.showConfirmDialog(null, "Jesteś pewny, że chcesz usunąć wszystkie elementy?", "Usunięcie elementów", JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            for (int i = 0; i < searchList.getSize(); i++) {
                db.deleteExpression(searchList.getElementAt(i).toString(), true);
            }
            searchList.clear();
        }
    }//GEN-LAST:event_clearHistoryButtonActionPerformed

    private void searchSelectedButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchSelectedButtonActionPerformed
        if (!searchHistoryList.isSelectionEmpty()) {
            currentExpression = searchHistoryList.getSelectedValue();
            this.results = searchLucene();
        } else {
            JOptionPane.showMessageDialog(this, "Musisz wybrać element!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_searchSelectedButtonActionPerformed

    private void searchClearFormButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchClearFormButtonActionPerformed
        lockResultsNavigation();
        searchExpressionTextField.setText("");
        currentExpression = "";
        resultsList.clear();
        searchTotalSizeValueLabel.setText("0");
        searchCurrentPageLabel.setText("0");
        searchSizeValueLabel.setText("0");
    }//GEN-LAST:event_searchClearFormButtonActionPerformed

    private void showSelectedResultButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSelectedResultButtonActionPerformed
        if (!searchResultsList.isSelectionEmpty()) {
            showSummary(results.summary.get(searchResultsList.getSelectedIndex()));
        } else {
            JOptionPane.showMessageDialog(this, "Musisz wybrać element!", "Błąd", JOptionPane.ERROR_MESSAGE);
        }
//String.format("<html><div WIDTH=%d>%s</div><html>", width, text);        
    }//GEN-LAST:event_showSelectedResultButtonActionPerformed

    private void searchIdValueLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchIdValueLabelMouseClicked
        try {
            openWebpage(new URL(this.url));
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_searchIdValueLabelMouseClicked

    private void resultJournalValueLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_resultJournalValueLabelMouseClicked
        try {
            openWebpage(new URL(this.journalURL));
        } catch (MalformedURLException ex) {
            Logger.getLogger(CrawlerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_resultJournalValueLabelMouseClicked

    private void startPageItemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_startPageItemActionPerformed
        Lucene index = new Lucene(false);
        dbStateValueLabel.setText(Long.toString(index.getTotalSize()));
        index.close();
        card.show(getContentPane(), "first");
    }//GEN-LAST:event_startPageItemActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JMenuItem aboutMenuItem;
    private javax.swing.JButton backToMainButton;
    private javax.swing.JButton clearHistoryButton;
    private javax.swing.JButton clearSearchButton;
    private javax.swing.JLabel currentPageLabel;
    private javax.swing.JLabel dbStateLabel;
    private javax.swing.JLabel dbStateValueLabel;
    private javax.swing.JButton deleteAllButton;
    private javax.swing.JButton deleteSelectedButton;
    private javax.swing.JLabel downloadActionLabel;
    private javax.swing.JLabel downloadActionValueLabel;
    private javax.swing.JLabel downloadHistoryLabel;
    private javax.swing.JList<String> downloadHistoryList;
    private javax.swing.JScrollPane downloadHistoryScrollPane;
    private javax.swing.JPanel downloadModeFrame;
    private javax.swing.JMenuItem downloadModeItem;
    private javax.swing.JLabel downloadModeLabel;
    private javax.swing.JSeparator downloadModeSeparator1;
    private javax.swing.JSeparator downloadModeSeparator2;
    private javax.swing.JSeparator downloadModeSeparator3;
    private javax.swing.JSeparator downloadModeSeparator4;
    private javax.swing.JProgressBar downloadProgressBar;
    private javax.swing.JButton downloadPubMedButton;
    private javax.swing.JPanel firstFrame;
    private javax.swing.JPanel helpFrame;
    private javax.swing.JMenu helpMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JLabel logoLabel;
    private javax.swing.JMenuBar mainMenu;
    private javax.swing.JMenu modeMenu;
    private javax.swing.JButton pauseDownloadButton;
    private javax.swing.JLabel quickStartLabel;
    private javax.swing.JMenuItem quickStartMenuItem;
    private javax.swing.JSeparator quickStartSeparator1;
    private javax.swing.JScrollPane resultAbstractScrollPane;
    private javax.swing.JScrollPane resultAbstractScrollPane1;
    private javax.swing.JTextArea resultAbstractTextArea;
    private javax.swing.JLabel resultArticleTitleLabel;
    private javax.swing.JLabel resultAuthorLabel;
    private javax.swing.JLabel resultAuthorValueLabel;
    private javax.swing.JLabel resultCountLabel;
    private javax.swing.JLabel resultCountValueLabel;
    private javax.swing.JPanel resultFrame;
    private javax.swing.JLabel resultJournalLabel;
    private javax.swing.JLabel resultJournalValueLabel;
    private javax.swing.JTextArea resultMeshTextArea;
    private javax.swing.JLabel resultRevisionDateLabel;
    private javax.swing.JLabel resultRevisionDateValueLabel;
    private javax.swing.JButton searchClearFormButton;
    private javax.swing.JLabel searchCurrentPageLabel;
    private javax.swing.JLabel searchDescriptionLabel;
    private javax.swing.JButton searchExpressionButton;
    private javax.swing.JLabel searchExpressionLabel;
    private javax.swing.JTextField searchExpressionTextField;
    private javax.swing.JLabel searchHistoryLabel;
    private javax.swing.JList<String> searchHistoryList;
    private javax.swing.JScrollPane searchHistoryScrollPane;
    private javax.swing.JLabel searchIdValueLabel;
    private javax.swing.JButton searchLocalBaseButton;
    private javax.swing.JPanel searchModeFrame;
    private javax.swing.JMenuItem searchModeItem;
    private javax.swing.JLabel searchModeLabel;
    private javax.swing.JLabel searchModeLabel1;
    private javax.swing.JSeparator searchModeSeparator1;
    private javax.swing.JSeparator searchModeSeparator2;
    private javax.swing.JSeparator searchModeSeparator3;
    private javax.swing.JButton searchPubMedButton;
    private javax.swing.JLabel searchPubMedLabel;
    private javax.swing.JTextField searchPubMedTextField;
    private javax.swing.JButton searchResultsBackButton;
    private javax.swing.JLabel searchResultsLabel;
    private javax.swing.JList<String> searchResultsList;
    private javax.swing.JButton searchResultsNextButton;
    private javax.swing.JScrollPane searchResultsScrollPane;
    private javax.swing.JButton searchSelectedButton;
    private javax.swing.JLabel searchSizeLabel;
    private javax.swing.JLabel searchSizeValueLabel;
    private javax.swing.JLabel searchTotalSizeLabel;
    private javax.swing.JLabel searchTotalSizeValueLabel;
    private javax.swing.JToggleButton showSelectedResultButton;
    private javax.swing.JMenu startMenu;
    private javax.swing.JMenuItem startPageItem;
    private javax.swing.JButton stopDownloadButton;
    private javax.swing.JButton updateAllTermsButton;
    private javax.swing.JButton updateLocalBaseButton;
    private javax.swing.JButton updateSelectedTermsButton;
    // End of variables declaration//GEN-END:variables
}
