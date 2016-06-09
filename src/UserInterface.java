import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;

import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.GridBagConstraints;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.Insets;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import opennlp.tools.cmdline.parser.ParserTool;
import opennlp.tools.namefind.NameFinderME;
import opennlp.tools.namefind.TokenNameFinderModel;
import opennlp.tools.parser.Parse;
import opennlp.tools.parser.Parser;
import opennlp.tools.parser.ParserFactory;
import opennlp.tools.parser.ParserModel;
import opennlp.tools.tokenize.Tokenizer;
import opennlp.tools.tokenize.TokenizerME;
import opennlp.tools.tokenize.TokenizerModel;
import opennlp.tools.tokenize.WhitespaceTokenizer;
import opennlp.tools.util.Span;

public class UserInterface {

	public JFrame frame;
	int v=0,w=1,x=2,y=3,z=4,Similar=0,anx=0,anx2=0,score=0;
	public String Answer, ANS;
	public String folderPath = "./resource";
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) throws IOException {	
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserInterface window = new UserInterface();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
}
	/**
	 * Create the application.
	 */
	public UserInterface() {
		initialize();
	}
	/**
	 * Initialize the contents of the frame.
	 * 
	 */
	private void initialize(){
		
		final Image image = requestImage();
		
		getInput file = new getInput(folderPath+"/text/input.txt");
		getInput file2 = new getInput(folderPath+"/text/KnowledgeBase.txt");
		
		try {
			String[] Questionlines = file.OpenFile();
			String[] KnowledgeBase = file2.OpenFile();
			
			frame = new JFrame();
			frame.setTitle("Astucieux");
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setBounds(0, 0, 960, 540);
			frame.setLocationRelativeTo(null);
			
			JPanel panel = new JPanel() {
	           @Override
	            protected void paintComponent(Graphics g) {
	                super.paintComponent(g);
	                g.drawImage(image, 0, 0, null);
	            }
	        };
			frame.getContentPane().add(panel, BorderLayout.CENTER);
			GridBagLayout gbl_panel = new GridBagLayout();
			gbl_panel.columnWidths = new int[]{0, 0, 0};
			gbl_panel.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
			gbl_panel.columnWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
			gbl_panel.rowWeights = new double[]{0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, Double.MIN_VALUE};
			panel.setLayout(gbl_panel);
			
			JLabel lblAstucieux = new JLabel("Question Answering System");
    		Font font = new Font("PAPYRUS", Font.BOLD, 36);
    		lblAstucieux.setFont(font);
    		lblAstucieux.setForeground(Color.WHITE);
			GridBagConstraints gbc_lblAstucieux = new GridBagConstraints();
			gbc_lblAstucieux.gridwidth = 2;
			gbc_lblAstucieux.insets = new Insets(0, 0, 5, 0);
			gbc_lblAstucieux.gridx = 0;
			gbc_lblAstucieux.gridy = 1;
			panel.add(lblAstucieux, gbc_lblAstucieux);
			
			//display the question
			JTextArea lblQuestion = new JTextArea(Questionlines[v]);
    		Font font1 = new Font("MONAC0", Font.PLAIN, 20);
    		lblQuestion.setFont(font1);
    		lblQuestion.setForeground(Color.WHITE);
			lblQuestion.setBounds(20, 20, 800, 150);
			lblQuestion.setLineWrap(true);
			lblQuestion.setOpaque(false);
			lblQuestion.setBackground(new Color(0,0,0,0));
			GridBagConstraints gbc_lblQuestion = new GridBagConstraints();
			gbc_lblQuestion.gridwidth = 2;
			gbc_lblQuestion.insets = new Insets(0, 0, 5, 0);
			gbc_lblQuestion.gridx = 0;
			gbc_lblQuestion.gridy = 2;
			panel.add(lblQuestion, gbc_lblQuestion);
			
			//display the first answer choice
			JButton btnNewButton = new JButton(Questionlines[w]);
    		Font font2 = new Font("NOTEWORTHY", Font.PLAIN, 20);
    		btnNewButton.setFont(font2);
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
            		anx=1;
            		btnNewButton.setForeground(Color.BLUE);
				}
			});
			btnNewButton.setHorizontalAlignment(SwingConstants.LEFT);
			GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
			gbc_btnNewButton.gridwidth = 2;
			gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton.gridx = 0;
			gbc_btnNewButton.gridy = 3;
			panel.add(btnNewButton, gbc_btnNewButton);
			
			//display the second answer choice			
			JButton btnNewButton_1 = new JButton(Questionlines[x]);
    		Font font3 = new Font("NOTEWORTHY", Font.PLAIN, 20);
    		btnNewButton_1.setFont(font3);
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
            		anx=2;
            		btnNewButton_1.setForeground(Color.BLUE);
				}
			});
			GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
			gbc_btnNewButton_1.gridwidth = 2;
			gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton_1.gridx = 0;
			gbc_btnNewButton_1.gridy = 4;
			panel.add(btnNewButton_1, gbc_btnNewButton_1);
			
			//display the third answer choice			
			JButton btnNewButton_2 = new JButton(Questionlines[y]);
    		Font font4 = new Font("NOTEWORTHY", Font.PLAIN, 20);
    		btnNewButton_2.setFont(font4);
			btnNewButton_2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
            		anx=3;
            		btnNewButton_2.setForeground(Color.BLUE);
				}
			});
			GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
			gbc_btnNewButton_2.gridwidth = 2;
			gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton_2.gridx = 0;
			gbc_btnNewButton_2.gridy = 5;
			panel.add(btnNewButton_2, gbc_btnNewButton_2);
			
			//display the fourth answer choice			
			JButton btnNewButton_3 = new JButton(Questionlines[z]);
    		Font font5 = new Font("NOTEWORTHY", Font.PLAIN, 20);
    		btnNewButton_3.setFont(font5);
			btnNewButton_3.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
            		anx=4;
            		btnNewButton_3.setForeground(Color.BLUE);
				}
			});
			GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
			gbc_btnNewButton_3.gridwidth = 2;
			gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
			gbc_btnNewButton_3.gridx = 0;
			gbc_btnNewButton_3.gridy = 6;
			panel.add(btnNewButton_3, gbc_btnNewButton_3);
			
			JTextArea scoremark = new JTextArea();
			scoremark.setOpaque(false);
			scoremark.setBackground(new Color(0,0,0,0));
			scoremark.setVisible(false);
    		Font font8 = new Font("MONAC0", Font.PLAIN, 50);
    		scoremark.setFont(font8);
    		scoremark.setForeground(Color.WHITE);
			GridBagConstraints gbc_scoremark = new GridBagConstraints();
			gbc_scoremark.gridwidth = 2;
			gbc_scoremark.fill = GridBagConstraints.VERTICAL;
			gbc_scoremark.insets = new Insets(0, 0, 5, 0);
			gbc_scoremark.gridx = 0;
			gbc_scoremark.gridy = 3;
			panel.add(scoremark, gbc_scoremark);
			
			JTextArea Finalscore = new JTextArea();
			Finalscore.setOpaque(false);
			Finalscore.setBackground(new Color(0,0,0,0));
			Finalscore.setVisible(false);
    		Font font7 = new Font("MONAC0", Font.PLAIN, 18);
    		Finalscore.setFont(font7);
    		Finalscore.setForeground(Color.WHITE);
			GridBagConstraints gbc_Finalscore = new GridBagConstraints();
			gbc_Finalscore.gridwidth = 2;
			gbc_Finalscore.fill = GridBagConstraints.VERTICAL;
			gbc_Finalscore.insets = new Insets(0, 0, 5, 0);
			gbc_Finalscore.gridx = 0;
			gbc_Finalscore.gridy = 2;
			panel.add(Finalscore, gbc_Finalscore);
			
			JLabel lblExplain = new JLabel();
			lblExplain.setVisible(false);
			  try {
				    Image img = ImageIO.read(new File(folderPath+"/images/Correct.png"));
				    lblExplain.setIcon(new ImageIcon(img));
				  } 
			  		catch (IOException ex) {
			            ex.printStackTrace();
				  }
			GridBagConstraints gbc_lblExplain = new GridBagConstraints();
			gbc_lblExplain.gridheight = 2;
			gbc_lblExplain.gridx = 1;
			gbc_lblExplain.gridy = 5;
			panel.add(lblExplain, gbc_lblExplain);
			
			JTextArea lblExplaination = new JTextArea("Explaination:");
			lblExplaination.setOpaque(false);
			lblExplaination.setBackground(new Color(0,0,0,0));
			lblExplaination.setVisible(false);
			GridBagConstraints gbc_lblExplaination = new GridBagConstraints();
			gbc_lblExplaination.gridwidth = 2;
			gbc_lblExplaination.fill = GridBagConstraints.VERTICAL;
			gbc_lblExplaination.insets = new Insets(0, 0, 5, 0);
			gbc_lblExplaination.gridx = 0;
			gbc_lblExplaination.gridy = 7;
			panel.add(lblExplaination, gbc_lblExplaination);
			
			JTextArea lblAns = new JTextArea(Questionlines[v]);
			lblAns.setBounds(10, 10, 800, 150);
			lblAns.setLineWrap(true);
			lblAns.setOpaque(false);
			lblAns.setBackground(new Color(0,0,0,0));
    		Font font6 = new Font("MONAC0", Font.PLAIN, 18);
    		lblAns.setFont(font6);
    		lblAns.setForeground(Color.WHITE);
			lblAns.setVisible(false);
			GridBagConstraints gbc_lblAns = new GridBagConstraints();
			gbc_lblAns.gridwidth = 2;
			gbc_lblAns.insets = new Insets(0, 0, 5, 0);
			gbc_lblAns.gridx = 0;
			gbc_lblAns.gridy = 8;
			panel.add(lblAns, gbc_lblAns);
			
			//Check Answer Button (Execute the process of retrieving answer from KnowledgeBase to answer the question)
			JButton btnAnswer = new JButton("CHECK THE ANSWER");
			btnAnswer.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					//tokenization
					if (anx != 0){
					String tokens[] = WhitespaceTokenizer.INSTANCE.tokenize(Questionlines[v]);
					int ans = 60;
					for (int i=0;i<50;i++){
						String token[] = WhitespaceTokenizer.INSTANCE.tokenize(KnowledgeBase[i]);
						int similar = 0;
						double sim = 0;
						double total = 0;
							for(String s : tokens){
					        	total++;
					            for(String s2 : token){
					                if (s.equals(s2)){
					                	sim++;
					                }
					            }
					        }
							similar = (int) ((sim/total)*100);
							if (similar > ans){
								ans = similar;
								ANS = KnowledgeBase[i];
								Answer = KnowledgeBase[i]+". (Confidence:"+similar+"%)";
							}				
					}
					String tokenAns[] = WhitespaceTokenizer.INSTANCE.tokenize(ANS);
					String tokenA[] = WhitespaceTokenizer.INSTANCE.tokenize(Questionlines[w]);
					String tokenB[] = WhitespaceTokenizer.INSTANCE.tokenize(Questionlines[x]);
					String tokenC[] = WhitespaceTokenizer.INSTANCE.tokenize(Questionlines[y]);
					String tokenD[] = WhitespaceTokenizer.INSTANCE.tokenize(Questionlines[z]);
					int similar=0,similar2=0,similar3=0,similar4=0;
					for (String s3 : tokenAns){
						for(String s4 :tokenA){
			                if (s3.equals(s4)){
			                	similar++;
			                }
						}
						for(String s5 :tokenB){
			                if (s3.equals(s5)){
			                	similar2++;
			                }
						}
						for(String s6 :tokenC){
			                if (s3.equals(s6)){
			                	similar3++;
			                }
						}
						for(String s7 :tokenD){
			                if (s3.equals(s7)){
			                	similar4++;
			                }
						}
					}

                	if (similar>similar2 && similar>similar3 && similar>similar4){
                		anx2=1;
                		btnNewButton.setForeground(Color.GREEN);
                		btnNewButton_1.setForeground(Color.RED);
                		btnNewButton_2.setForeground(Color.RED);
                		btnNewButton_3.setForeground(Color.RED);
                	}
                	else if (similar2>similar && similar2>similar3 && similar2>similar4){
                		anx2=2;
                		btnNewButton.setForeground(Color.RED);
                		btnNewButton_1.setForeground(Color.GREEN);
                		btnNewButton_2.setForeground(Color.RED);
                		btnNewButton_3.setForeground(Color.RED);
                	}
                	else if (similar3>similar && similar3>similar2 && similar3>similar4){
                		anx2=3;
                		btnNewButton.setForeground(Color.RED);
                		btnNewButton_1.setForeground(Color.RED);
                		btnNewButton_2.setForeground(Color.GREEN);
                		btnNewButton_3.setForeground(Color.RED);

                	}
                	else if (similar4>similar && similar4>similar2 && similar4>similar3){
                		anx2=4;
                		btnNewButton.setForeground(Color.RED);
                		btnNewButton_1.setForeground(Color.RED);
                		btnNewButton_2.setForeground(Color.RED);
                		btnNewButton_3.setForeground(Color.GREEN);
                	}
                	if (anx == anx2){
                		Font font = new Font("CHALKDUSTER", Font.BOLD, 16);
                		lblExplaination.setFont(font);
                		lblExplaination.setForeground(Color.GREEN);
                		lblExplaination.setText("Congratulation, you got it Correct!!");
                		score++;
    					lblAns.setText(Answer);
                	} else {
                		Font font = new Font("CHALKDUSTER", Font.BOLD, 16);
                		lblExplaination.setFont(font);
                		lblExplaination.setForeground(Color.RED);
                		lblExplaination.setText("Unfortunately, you got it Wrong!!");
    					lblAns.setText(Answer);
                	}
					lblExplaination.setVisible(true);
					lblAns.setVisible(true);
					anx=0;
					
					/*
				    //Name-Entity-Recognition
					String[] sentences = {Questionlines[v]}; 
					try (InputStream tokenStream = new FileInputStream(new File(folderPath+"/Model/en-token.bin"));)
					{
						TokenizerModel tokenModel = new TokenizerModel(tokenStream);
						Tokenizer tokenizer = new TokenizerME(tokenModel);
						
						String[] modelNames = {"en-ner-person.bin","en-ner-location.bin","en-ner-organization.bin","en-ner-time.bin","en-ner-percentage.bin","en-ner-money.bin","en-ner-date.bin"};
							
						ArrayList<String> list = new ArrayList<String>();
						
						for (String name: modelNames) {
							TokenNameFinderModel entityModel = new TokenNameFinderModel(new FileInputStream(new File(folderPath+"/Model/"+name)));
							NameFinderME nameFinder = new NameFinderME(entityModel);
						
							for (int index=0; index< sentences.length; index++) {
								String tokens1[] = tokenizer.tokenize(sentences[index]);
								Span nameSpans[] = nameFinder.find(tokens1);
								
								for (Span span : nameSpans) {
									list.add("Sentence: "+ index + " Span: " + span.toString() + "Entity: " + tokens1[span.getStart()]);
								}
							}
						}
					} catch (FileNotFoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					//Parsing
					try (InputStream modelInputStream = new FileInputStream(new File(folderPath+"/Model/en-parser-chunking.bin"));){
						ParserModel model = new ParserModel(modelInputStream);
						Parser parser = ParserFactory.create(model);			

						String sentence = Questionlines[v];
						Parse Parses[] = ParserTool.parseLine(sentence, parser, 1);
						
						for (Parse parse : Parses) {
							parse.show();
						}
					
					} catch (FileNotFoundException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					} catch (IOException e2) {
						// TODO Auto-generated catch block
						e2.printStackTrace();
					}*/				
					}
				}
			});
			GridBagConstraints gbc_btnAnswer = new GridBagConstraints();
			gbc_btnAnswer.gridwidth = 2;
			gbc_btnAnswer.gridx = 0;
			gbc_btnAnswer.gridy = 9;
			panel.add(btnAnswer, gbc_btnAnswer);
			
			// Previous Button
			JButton btnNext2 = new JButton();
			btnNext2.setEnabled(false);
			btnNext2.setOpaque(false);
			btnNext2.setContentAreaFilled(false);
			btnNext2.setBorderPainted(false);
			  try {
				    Image img = ImageIO.read(new File(folderPath+"/images/Previous.png"));
				    btnNext2.setIcon(new ImageIcon(img));
				  } 
			  		catch (IOException ex) {
			            ex.printStackTrace();
				  }
			btnNext2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					v=v-5;w=w-5;x=x-5;y=y-5;z=z-5;
					lblQuestion.setText(Questionlines[v]);
					btnNewButton.setText(Questionlines[w]);
					btnNewButton_1.setText(Questionlines[x]);
					btnNewButton_2.setText(Questionlines[y]);
					btnNewButton_3.setText(Questionlines[z]);
					lblAns.setVisible(false);
					lblExplaination.setVisible(false);
					lblExplain.setVisible(false);
					if (v>0) {
						btnNext2.setEnabled(true);
					} else {
						btnNext2.setEnabled(false);
					}
            		btnNewButton.setForeground(Color.BLACK);
            		btnNewButton_1.setForeground(Color.BLACK);
            		btnNewButton_2.setForeground(Color.BLACK);
            		btnNewButton_3.setForeground(Color.BLACK);
				}
			});
			GridBagConstraints gbc_btnNext2 = new GridBagConstraints();
			gbc_btnNext2.insets = new Insets(0, 0, 0, 5);
			gbc_btnNext2.gridx = 0;
			gbc_btnNext2.gridy = 9;
			panel.add(btnNext2, gbc_btnNext2);
			
			// Next Button
			JButton btnNext = new JButton();
			btnNext.setOpaque(false);
			btnNext.setContentAreaFilled(false);
			btnNext.setBorderPainted(false);
			  try {
				    Image img = ImageIO.read(new File(folderPath+"/images/Next.png"));
				    btnNext.setIcon(new ImageIcon(img));
				  } 
			  		catch (IOException ex) {
			            ex.printStackTrace();
				  }
			btnNext.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					v=v+5;w=w+5;x=x+5;y=y+5;z=z+5;
					lblQuestion.setText(Questionlines[v]);
					btnNewButton.setText(Questionlines[w]);
					btnNewButton_1.setText(Questionlines[x]);
					btnNewButton_2.setText(Questionlines[y]);
					btnNewButton_3.setText(Questionlines[z]);
					lblAns.setVisible(false);
					lblExplaination.setVisible(false);
					lblExplain.setVisible(false);
					btnNext2.setEnabled(true);
					if (v < 250) {
						btnNext.setVisible(true);
						btnNext.setEnabled(true);	
					} else {
						btnNext.setEnabled(false);
						btnNext2.setEnabled(false);
						btnAnswer.setVisible(false);
						lblAns.setVisible(false);
						lblExplaination.setVisible(false);
						lblQuestion.setVisible(false);
						btnNewButton.setVisible(false);
						btnNewButton_1.setVisible(false);
						btnNewButton_2.setVisible(false);
						btnNewButton_3.setVisible(false);
						Finalscore.setText("Final Score :");
						scoremark.setText(score+"/50");
						Finalscore.setVisible(true);
						scoremark.setVisible(true);
						lblExplain.setVisible(true);
						JButton Reset = new JButton("RESTART");
						Reset.setVisible(true);
						Reset.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
									v=0;w=1;x=2;y=3;z=4;score=0;
									lblQuestion.setText(Questionlines[v]);
									btnNewButton.setText(Questionlines[w]);
									btnNewButton_1.setText(Questionlines[x]);
									btnNewButton_2.setText(Questionlines[y]);
									btnNewButton_3.setText(Questionlines[z]);
									lblQuestion.setVisible(true);
									btnNewButton.setVisible(true);
									btnNewButton_1.setVisible(true);
									btnNewButton_2.setVisible(true);
									btnNewButton_3.setVisible(true);
									btnNext.setEnabled(true);
									btnNext2.setEnabled(false);
									btnAnswer.setVisible(true);
									Finalscore.setVisible(false);
									scoremark.setVisible(false);
									lblExplain.setVisible(false);
									Reset.setVisible(false);
							}
						});			
						GridBagConstraints gbc_Reset = new GridBagConstraints();
						gbc_Reset.gridwidth = 2;
						gbc_Reset.gridx = 0;
						gbc_Reset.gridy = 9;
						panel.add(Reset, gbc_Reset);
					}
            		btnNewButton.setForeground(Color.BLACK);
            		btnNewButton_1.setForeground(Color.BLACK);
            		btnNewButton_2.setForeground(Color.BLACK);
            		btnNewButton_3.setForeground(Color.BLACK);
				}
			});
			GridBagConstraints gbc_btnNext = new GridBagConstraints();
			gbc_btnNext.gridx = 1;
			gbc_btnNext.gridy = 9;
			panel.add(btnNext, gbc_btnNext);
			
		} 
		catch (IndexOutOfBoundsException e) {
			System.err.println("IndexOutOfBoundsException: " + e.getMessage());
			System.exit(0);
		} catch (IOException e) {
			System.err.println("Caught IOException: " + e.getMessage());
		}
	}
	
	//load image for background
	private Image requestImage() {
        Image image = null;
	        try {
	            image = ImageIO.read(new File(folderPath+"/images/Background.jpg"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
        return image;
    }
}
