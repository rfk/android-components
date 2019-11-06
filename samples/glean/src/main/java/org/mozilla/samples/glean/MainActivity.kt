/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package org.mozilla.samples.glean

import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import mozilla.components.service.experiments.Experiments
import mozilla.appservices.demothing.DemoThing
import org.mozilla.samples.glean.GleanMetrics.Test
import org.mozilla.samples.glean.GleanMetrics.BrowserEngagement
import org.mozilla.samples.glean.library.SamplesGleanLibrary

open class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Generate an event when user clicks on the button.
        buttonGenerateData.setOnClickListener {
            // Increments the test_counter metric from the metrics.yaml file.
            Test.counter.add()

            // Do the thing.
            // This will emit some metrics from inside the component.
            DemoThing().doTheThing(9000)

            // This is referencing the event ping named 'click' from the metrics.yaml file. In
            // order to illustrate adding extra information to the event, it is also adding to the
            // 'extras' field a dictionary of values.  Note that the dictionary keys must be
            // declared in the metrics.yaml file under the 'extra_keys' section of an event metric.
            BrowserEngagement.click.record(
                    mapOf(
                            BrowserEngagement.clickKeys.key1 to "extra_value_1",
                            BrowserEngagement.clickKeys.key2 to "extra_value_2"
                    )
            )
        }

        Test.timespan.stop()

        // Update some metrics from a third-party library
        SamplesGleanLibrary.recordMetric()
        SamplesGleanLibrary.recordExperiment()
    }
}
