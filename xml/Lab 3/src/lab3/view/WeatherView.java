package lab3.view;

import java.awt.Color;
import java.awt.Image;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import lab3.model.WeatherModel;

public class WeatherView extends javax.swing.JFrame {
    
    private final WeatherModel MODEL;

    public WeatherView() throws Exception {
        this.MODEL = new WeatherModel();
        
        initComponents();
        updateWeatherInfo(MODEL);
    }
    
    private void updateWeatherInfo(WeatherModel model) {
        cityLabel.setText(model.getCityName());
        timeLabel.setText("Cập nhật: " + model.getLastUpdate());
        tempLabel.setText(model.getTemperature() + "°C");
        descLabel.setText(convertToTitleCaseIteratingChars(model.getWeatherCondition()));
        feelsLikeLabel.setText("Cảm giác như: " + model.getFeelLike() + "°C");
        humidityLabel.setText("Độ ẩm: " + model.getHumidity());
        pressureLabel.setText("Áp suất: " + model.getPressure());
        windSpeedLabel.setText("Gió: " + model.getWindSpeed() + "m/s");
        windDirLabel.setText("Hướng gió: " + model.getWindDirection());
        cloudsLabel.setText("Mây: " + model.getCloud() + "%");
        visibilityLabel.setText("Tầm nhìn: " + Double.parseDouble(model.getVisibility()) / 1000 + "km");
        
        String iconCode = model.getIconCode();
        if (iconCode != null && !iconCode.isEmpty()) {
            try {
                URL iconUrl = new URL("http://openweathermap.org/img/w/" + iconCode + ".png");
                ImageIcon icon = new ImageIcon(iconUrl);
                
                Image image = icon.getImage();
                Image scaledImage = image.getScaledInstance(150, 150, Image.SCALE_SMOOTH);
                ImageIcon scaledIcon = new ImageIcon(scaledImage);
        
                weatherIcon.setIcon(scaledIcon);
            } catch (Exception e) {
                weatherIcon.setIcon(null);
            }
        }
        
        customizeWeatherDisplay(tempLabel, iconCode);
    }
    
    public void customizeWeatherDisplay(JLabel temperatureLabel, String iconCode) {
        Color tempColor = Color.BLACK;

        if (iconCode != null && !iconCode.isEmpty()) {
            if (iconCode.startsWith("01")) {
                tempColor = new Color(255, 98, 0);
            }
            
            else if (iconCode.startsWith("09") || iconCode.startsWith("10")) {
                tempColor = new Color(70, 130, 180);
            }
            
            else if (iconCode.startsWith("13")) {
                tempColor = new Color(135, 206, 235);
            }
            
            else if (iconCode.startsWith("03") || iconCode.startsWith("04")) {
                tempColor = new Color(128, 128, 128);
            }
            
            else if (iconCode.startsWith("50")) {
                tempColor = new Color(211, 211, 211);
            }
        }

        temperatureLabel.setForeground(tempColor);
    }

    public String convertToTitleCaseIteratingChars(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }

        StringBuilder converted = new StringBuilder();

        boolean convertNext = true;
        for (char ch : text.toCharArray()) {
            if (Character.isSpaceChar(ch)) {
                convertNext = true;
            } else if (convertNext) {
                ch = Character.toTitleCase(ch);
                convertNext = false;
            } else {
                ch = Character.toLowerCase(ch);
            }
            converted.append(ch);
        }

        return converted.toString();
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        java.awt.GridBagConstraints gridBagConstraints;

        mainPanel = new javax.swing.JPanel();
        headerPanel = new javax.swing.JPanel();
        cityLabel = new javax.swing.JLabel();
        timeLabel = new javax.swing.JLabel();
        centerPanel = new javax.swing.JPanel();
        weatherIcon = new javax.swing.JLabel();
        tempLabel = new javax.swing.JLabel();
        descLabel = new javax.swing.JLabel();
        feelsLikeLabel = new javax.swing.JLabel();
        detailsPanel = new javax.swing.JPanel();
        humidityLabel = new javax.swing.JLabel();
        pressureLabel = new javax.swing.JLabel();
        windSpeedLabel = new javax.swing.JLabel();
        windDirLabel = new javax.swing.JLabel();
        cloudsLabel = new javax.swing.JLabel();
        visibilityLabel = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ứng dụng Thời tiết");
        setPreferredSize(new java.awt.Dimension(400, 600));
        setSize(new java.awt.Dimension(600, 500));

        mainPanel.setBorder(javax.swing.BorderFactory.createEmptyBorder(20, 20, 20, 20));
        mainPanel.setLayout(new java.awt.BorderLayout(10, 10));

        headerPanel.setOpaque(false);
        headerPanel.setLayout(new java.awt.BorderLayout(0, 10));

        cityLabel.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
        cityLabel.setForeground(new java.awt.Color(107, 78, 170));
        cityLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cityLabel.setText("Đà Nẵng, VN");
        headerPanel.add(cityLabel, java.awt.BorderLayout.NORTH);

        timeLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        timeLabel.setForeground(new java.awt.Color(122, 122, 122));
        timeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        timeLabel.setText("Cập nhật:");
        headerPanel.add(timeLabel, java.awt.BorderLayout.SOUTH);

        mainPanel.add(headerPanel, java.awt.BorderLayout.PAGE_START);

        centerPanel.setOpaque(false);
        centerPanel.setLayout(new java.awt.GridBagLayout());

        weatherIcon.setFont(new java.awt.Font("Arial", 0, 80)); // NOI18N
        weatherIcon.setForeground(new java.awt.Color(255, 255, 0));
        weatherIcon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 0;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        centerPanel.add(weatherIcon, gridBagConstraints);

        tempLabel.setFont(new java.awt.Font("Arial", 1, 48)); // NOI18N
        tempLabel.setForeground(new java.awt.Color(255, 98, 0));
        tempLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        tempLabel.setText("24°C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 1;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        centerPanel.add(tempLabel, gridBagConstraints);

        descLabel.setFont(new java.awt.Font("Arial", 2, 18)); // NOI18N
        descLabel.setForeground(new java.awt.Color(74, 144, 226));
        descLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descLabel.setText("Mây cụm");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 2;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        centerPanel.add(descLabel, gridBagConstraints);

        feelsLikeLabel.setFont(new java.awt.Font("Arial", 0, 16)); // NOI18N
        feelsLikeLabel.setForeground(new java.awt.Color(74, 144, 226));
        feelsLikeLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        feelsLikeLabel.setText("Cảm giác như: 24.4°C");
        gridBagConstraints = new java.awt.GridBagConstraints();
        gridBagConstraints.gridx = 0;
        gridBagConstraints.gridy = 3;
        gridBagConstraints.insets = new java.awt.Insets(10, 10, 10, 10);
        centerPanel.add(feelsLikeLabel, gridBagConstraints);

        mainPanel.add(centerPanel, java.awt.BorderLayout.CENTER);

        detailsPanel.setOpaque(false);
        detailsPanel.setLayout(new java.awt.GridLayout(4, 2, 10, 10));

        humidityLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        humidityLabel.setText("Độ ẩm: 73%");
        detailsPanel.add(humidityLabel);

        pressureLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        pressureLabel.setText("Áp suất: 1014 hPa");
        detailsPanel.add(pressureLabel);

        windSpeedLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        windSpeedLabel.setText("Gió: 5.14 m/s");
        detailsPanel.add(windSpeedLabel);

        windDirLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        windDirLabel.setText("Hướng gió: Bắc-Đông-Bắc");
        detailsPanel.add(windDirLabel);

        cloudsLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        cloudsLabel.setText("Mây: 75%");
        detailsPanel.add(cloudsLabel);

        visibilityLabel.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        visibilityLabel.setText("Tầm nhìn: 8 km\"");
        detailsPanel.add(visibilityLabel);

        mainPanel.add(detailsPanel, java.awt.BorderLayout.PAGE_END);

        getContentPane().add(mainPanel, java.awt.BorderLayout.CENTER);

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    public static void main(String args[]) {
        try {
            UIManager.setLookAndFeel(
                    UIManager.getSystemLookAndFeelClassName()
            );
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(WeatherView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(WeatherView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(WeatherView.class.getName()).log(Level.SEVERE, null, ex);
        } catch (UnsupportedLookAndFeelException ex) {
            Logger.getLogger(WeatherView.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new WeatherView().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(WeatherView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel centerPanel;
    private javax.swing.JLabel cityLabel;
    private javax.swing.JLabel cloudsLabel;
    private javax.swing.JLabel descLabel;
    private javax.swing.JPanel detailsPanel;
    private javax.swing.JLabel feelsLikeLabel;
    private javax.swing.JPanel headerPanel;
    private javax.swing.JLabel humidityLabel;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JLabel pressureLabel;
    private javax.swing.JLabel tempLabel;
    private javax.swing.JLabel timeLabel;
    private javax.swing.JLabel visibilityLabel;
    private javax.swing.JLabel weatherIcon;
    private javax.swing.JLabel windDirLabel;
    private javax.swing.JLabel windSpeedLabel;
    // End of variables declaration//GEN-END:variables

}
