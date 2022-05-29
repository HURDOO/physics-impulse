module physics.impulse.main {
    requires javafx.graphics;
    requires javafx.fxml;
    requires javafx.controls;

    opens kr.kro.hurdoo.physics_impulse to javafx.graphics,javafx.fxml,javafx.controls;

    exports kr.kro.hurdoo.physics_impulse to javafx.graphics,javafx.fxml,javafx.controls;
}