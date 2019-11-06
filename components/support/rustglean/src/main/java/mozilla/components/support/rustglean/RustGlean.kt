/* This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at http://mozilla.org/MPL/2.0/. */

package mozilla.components.support.rustglean

import android.os.SystemClock
import mozilla.appservices.rcglean.RcGlean
import mozilla.telemetry.glean.Glean

object RustGlean {

    fun enable() {
        RcGlean().initialize(Glean.getHandle(), SystemClock.elapsedRealtimeNanos())
    }

}
