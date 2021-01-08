package logs

import (
	"testing"
)

func TestLogger(t *testing.T) {
	t.Run("TextLogger", func(t *testing.T) {
		log := NewTextLogger()
		log.Infof("This is a test log: %s", "info")
		log.Errorf("This is an error log: %s", "Error log")
	})

	t.Run("JSONLogger", func(t *testing.T) {
		log := NewJSONLogger()
		log.Infof("This is a test log: %s", "info")
		log.Errorf("This is an error log: %s", "Error log")
	})
}
